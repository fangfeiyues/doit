### NIO
与IO的区别
> 1. Stream与Buffer, 流是以顺序的方式读取一个或多个字节而Buffer在从Channel中读取数据后就可以随机的操作
> 2. 阻塞与非阻塞, 如流在read的时候当前线程是阻塞的但是NIO不会阻塞
> 3. selector多路复用选择器, Channel注册到Selector之后会不断循环查询(select)这些注册的Channel是否有已就绪的IO事件。一个线程管理多个Channel
  
  Channel:
1.FileChannel
2.SocketChannel
   SocketChannel sc = ((ServerSocketChannel) k.channel()).accept();
   HAConnection -- socketChannel.configureBlocking(false)...
   WriteSocketService -- this.socketChannel.register(this.selector, SelectionKey.OP_WRITE);   可见NIO当Server拿到准备好的SocketChannel之后还会继续注册处理

  Buffer:
1. ByteBuffer buf = ByteBuffer.allocate(48);                          //在java堆直接分配
2. ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);	      // 直接内存分配
3. this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize);   
怎么理解2,3两点？ 分配的MappedByteBuffer已经直接映射到磁盘的位置了而堆外内存池还需要加载到FileChannel才能继续flush?

  Selector:
  在NIO中Selector.open()是单一线程的选择器(Netty就有多线程的选择器了 eventLoopGroupSelector ) 存在一个线程去不断接受Channel事件
1.如上HA 对于服务端每次接受的Channel都会注册到Write/Read选择器中。
2.如果一个Channel注册到选择器中它必须是非阻塞的 即FileChannel不行以为它是阻塞的
SelectionKey 
-- interest set感兴趣的事件集 int interestSet = selectionKey.interestOps();
-- Channel & Selector

### BIO & NIO & AIO
> IO Block, 是指操作系统可预见的这个Block才会主动Block。例如当读取TCP连接数据时如果发现Socket buffer没有数据 

BIO 调用read，如果发现没数据已经到达，当前线程就会Block住
    网络服务为了同时响应多个并发的网络请求，必须实现为多线程的.每个线程处理一个网络请求。线程数随着并发连接数线性增长
    1.线程越多，Context Switch就越多，而Context Switch是一个比较重的操作，会无谓浪费大量的CPU。
    2.每个线程会占用一定的内存作为线程的栈。比如有1000个线程同时运行，每个占用1MB内存，就占用了1个G的内存。
NIO 调用read，如果发现没数据已经到达，就会立刻返回-1, 并且errno被设为EAGAIN。
    当前线程可以先去做点其他事
    1.如果有大量文件描述符都要等，那么就得一个一个的read。这会带来大量的Context Switch（read是系统调用，每调用一次就得在用户态和核心态切换一次）
    2.休息一会的时间不好把握。这里是要猜多久之后数据才能到。等待时间设的太长，程序响应延迟就过大；设的太短，就会造成过于频繁的重试，干耗CPU而已。
IO-Multiplexing 程序注册一组socket文件描述符给操作系统，表示 "我要监视这些fd是否有IO事件发生，有了就告诉程序处理"
    a.IO多路复用是指多个数据流共享同一个Socket(X)。其实IO多路复用说的是多个Socket，只不过操作系统是一起监听他们的事件而已。但在HTTP2确实是多个数据流共享同一个TCP连接
    b.IO多路复用是NIO，所以总是不Block的(X)。
    c.IO多路复用和NIO一起减少了IO(X)。
    操作系统级别提供了一些接口来支持IO多路复用，最常用的是select和poll

    int select(int nfds, fd_set *readfds, fd_set *writefds, fd_set *exceptfds, struct timeval *timeout);
        1.把需要监听的事件放到数组，select监听fds的多个block读取时间此时select会处于阻塞
        2.有一个事件发生 遍历fds数组检查哪个事件到达
        3.读取，处理
    不足：
        1.select能够支持的最大的fd数组的长度是1024。这对要处理高并发的web服务器是不可接受的
        2.fd数组按照监听的事件分为了3个数组，为了这3个数组要分配3段内存去构造，而且每次调用select前都要重设它们（因为select会改这3个数组)；调用select后，这3数组要从用户态复制一份到内核态；事件到达后，要遍历这3数组。很不爽
        3.select是无状态的，即每次调用select，内核都要重新检查所有被注册的fd的状态。select返回后，这些状态就被返回了，内核不会记住它们；到了下一次调用，内核依然要重新检查一遍。于是查询的效率很低

     int poll(struct pollfd *fds, nfds_t nfds, int timeout);
         大多类似于select 不足之处也在于无状态且需要轮询数组

     int epfd = epoll_create(10);
     int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);

**epoll 比 select&poll 强的地方**
> 1. select和poll每次都需要把完成的fd列表传入到内核，迫使内核每次必须从头扫描到尾。而epoll完全是反过来的。
> 2. epoll在内核的数据被建立好了之后，每次某个被监听的fd一旦有事件发生，内核就直接标记之。
> 3. epoll_wait调用时，会尝试直接读取到当时已经标记好的fd列表，如果没有就会进入等待状态。
 
> 1. 水平触发（默认实现）。只关心文件描述符总是否还有没完成处理的数据，如果有就会一直处于被触发的状态这样如果在读取http请求数据
时我们只想读取一部分做判断时就会一直被socket还有数据事件打扰。
> 2. 边缘触发。只关心是否有新的事件，如果返回过一次不管程序是否处理了只要没有新的事件产生epoll_wait不会再认为这个fd被触发

【reactor线程模型】
Reactor：负责响应事件，将事件分发给绑定了该事件的Handler处理；
Handler：事件处理器，绑定了某类事件，负责执行对应事件的Task对事件进行处理；
Acceptor：Handler的一种，绑定了connect事件
当客户端发起connect请求时，Reactor会将accept事件分发给Acceptor处理

				
【Netty】
 ----------------------- 客户端 ---------------------------------------
 Bootstrap bootstrap = new Bootstrap();
 this.eventLoopGroupWorker = new NioEventLoopGroup();
 Bootstrap handler = this.bootstrap.group(this.eventLoopGroupWorker)
			.channel(NioSocketChannel.class) 		// channel向selector注册事件之后 后面的都是针对该channel的设置包括属性&pipeline
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.SO_KEEPALIVE, false)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, nettyClientConfig.getConnectTimeoutMillis())
            .option(ChannelOption.SO_SNDBUF, nettyClientConfig.getClientSocketSndBufSize())
            .option(ChannelOption.SO_RCVBUF, nettyClientConfig.getClientSocketRcvBufSize())
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    if (nettyClientConfig.isUseTLS()) {
                        if (null != sslContext) {
                            pipeline.addFirst(defaultEventExecutorGroup, "sslHandler", sslContext.newHandler(ch.alloc()));
                            log.info("Prepend SSL handler");
                        } else {
                            log.warn("Connections are insecure as SSLContext is null!");
                        }
                    }
                    pipeline.addLast(
                        defaultEventExecutorGroup,
                        new NettyEncoder(),
                        new NettyDecoder(),
                        new IdleStateHandler(0, 0, nettyClientConfig.getClientChannelMaxIdleTimeSeconds()),
                        new NettyConnectManageHandler(),
                        new NettyClientHandler());
                }
            });
 
   createChannel(addr) -- ChannelFuture = this.bootstrap.connect(RemotingHelper.string2SocketAddress(addr));
   
 ** NioEventLoopGroup **  
   - MultithreadEventExecutorGroup(int nThreads, ThreadFactory threadFactory, Object... args)		// 不填就默认 NettyRuntime.availableProcessors() * 2
   
 ** connect() **
	- ChannelFuture = initAndRegister() 
			// channel()初始化的channel, 
		- 1.channel = channelFactory.newChannel()
			- NioSocketChannel 														// SocketChannel = provider.openSocketChannel()
				- AbstractNioByteChannel(Channel parent, SelectableChannel ch)		// OP_READ
					- AbstractNioChannel(parent, ch, SelectionKey.OP_READ)			// ch.configureBlocking(false); this.readInterestOp = OP_READ;
						- AbstractChannel(null)										// id = newId(); 
						  - unsafe = newUnsafe();  
						  - pipeline = newChannelPipeline();		//!! 在Channel初始化时候必然ChannelPipeline的初始化(理解为管道形成了里面的事件也相应开始了)
							- TailContext  ChannelInboundHandler
							- HeadContext  ChannelOutboundHandler
		- 2.init(channel)  		
			 - Bootstrap
				 - ChannelInitializer.channelRegistered(ChannelHandlerContext)		// 将自定义的handler添加到ChannelPipeline中
				 - remove(ctx)														// 最后remove了
			 - ServerBootstrap		// Server分成两个Selector一个用于接受handler另一个则分配到Acceptor,
				- pipeline.addLast(handler);
					- newCtx = newContext(group, filterName(name, handler), handler);		// DefaultChannelHandlerContext
					- addLast0(newCtx)														// 把新的Handler插入到head tail的双向链表
						- ChannelInitializer 仅仅实现了 ChannelInboundHandler 接口, inbound = true, outbound = false.
				- pipeline.addLast(new ServerBootstrapAcceptor(ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
					- channelRead(ChannelHandlerContext ctx, Object msg)
						- child.pipeline().addLast(childHandler);		// 添加childHandler
						- childGroup.register(child).addListener		// 把channel注册到worker
				
	    - 3.ChannelFuture = config().group().register(channel)		
			- next().register(channel)																//1.Channel与对应的EventLoop关联
				- AbstractUnsafe # register(EventLoop, ChannelPromise)
				- register0(ChannelPromise)					// 1.Channel注册到Selector
				    - AbstractNioChannel#doRegister
						- selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this)		//2.将底层的 Java NIO SocketChannel 注册到指定的 selector 中
					- pipeline.fireChannelRegistered();		// 2.添加自定义handler 并串联
						- AbstractChannelHandlerContext#invokeChannelRegistered(head)  head = AbstractChannelHandlerContext
					

	- Bootstrap.doConnect(future.getNow(), localAddress, promise)
			- channel.eventLoop().execute()
				- ChannelFuture = AbstractChannel.connect(SocketAddress, ChannelPromise)
					- DefaultChannelPipeline.connect(remoteAddress, promise)
						- (AbstractChannelHandlerContext) tail.connect(remoteAddress, promise);	
							- AbstractChannelHandlerContext next = findContextOutbound()  			// DefaultChannelPipeline 内的双向链表的 tail 开始, 不断向前寻找第一个 outbound 为 true 的 AbstractChannelHandlerContext, 然后调用它的 invokeConnect 方法
							- next.invokeConnect(remoteAddress, localAddress, promise);
					
-------------------------- 服务端 ----------------------------------
 this.serverBootstrap = new ServerBootstrap();
			// NioEvenLoop聚合了多路复用器Selector可以同时并发处理成百上千个客户端Channel
            this.serverBootstrap.group(this.eventLoopGroupBoss, this.eventLoopGroupSelector)	
				// 构建ReflectiveChannelFactory到bootstrap, 最后生成的newChannel就是class
                .channel(useEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)   
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_SNDBUF, nettyServerConfig.getServerSocketSndBufSize())
                .childOption(ChannelOption.SO_RCVBUF, nettyServerConfig.getServerSocketRcvBufSize())
                .localAddress(new InetSocketAddress(this.nettyServerConfig.getListenPort()))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                            .addLast(defaultEventExecutorGroup, HANDSHAKE_HANDLER_NAME,
                                new HandshakeHandler(TlsSystemConfig.tlsMode))
                            .addLast(defaultEventExecutorGroup,
                                new NettyEncoder(),
                                new NettyDecoder(),
                                new IdleStateHandler(0, 0, nettyServerConfig.getServerChannelMaxIdleTimeSeconds()),
                                new NettyConnectManageHandler(),
                                new NettyServerHandler()
                            );
                    }
                });
            childHandler.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            ChannelFuture sync = this.serverBootstrap.bind().sync();
            InetSocketAddress addr = (InetSocketAddress) sync.channel().localAddress();

[Server和Client不一样的Channel]
bossGroup   -- NioServerSocketChannal 
workerGroup(childGroup) -- 把childHandler
	- ServerBootstrapAcceptor.channelRead(ChannelHandlerContext, Object msg)		
		- Channel child = (Channel) msg
		- child.pipeline().addLast(childHandler)
		- childGroup.register(child)
		
当一个client连接到server时, Java底层的NIO ServerSocketChannel会有一个SelectionKey.OP_ACCEPT就绪事件, 接着就会调用到 NioServerSocketChannel.doReadMessages
NioServerSocketChannel.doReadMessages(List<Object> buf)
	- SocketChannel ch = SocketUtils.accept(javaChannel());    // 获取客户端的新连接
	- buf.add(new NioSocketChannel(this, ch));

ChannelPipeline pipeline = ch.pipeline()
pipeline.addLast(handler)		// 为pipeline加handler
	- DefaultChannelPipeline#addLast(ChannelHandler... handlers)
		- addLast(EventExecutorGroup group, String name, ChannelHandler handler)
			- AbstractChannelHandlerContext newCtx = newContext(group, filterName(name, handler), handler);
			- callHandlerAdded0(newCtx);
				- ctx.callHandlerAdded();
					- AbstractChannelHandlerContext#callHandlerAdded()
						- ChannelInitializer#handlerAdded(ChannelHandlerContext ctx)
							- initChannel(ChannelHandlerContext ctx)
								- initChannel((C) ctx.channel());			// 
	
== [ NioEventLoop 及 NioServerSocketChannel 的注册]
NioEventLoop(selector, selectedKeys..)读取到消息之后直接调用ChannelPipeline的fireChannelRead.
   只要用户不主动切换线程一直都是由NioEventLoop调用用户的Handler期间不进行切换这样串行化方式避免了多线程竞争导致的锁竞争
EventLoop是一个单例线程池，每个EventLoop可以绑定多个Channel而每个Channel只能由一个EventLoop来处理
EventLoop 的作用是一个死循环，而这个循环中做3件事情：
1.有条件的等待 Nio 事件
2.处理 Nio 事件
3.处理消息队列中的任务
AbstractChannel.register(EventLoop, ChannelPromise)  //
	- SingleThreadEventExecutor.execute(Runnable){ ... }
		- addTask(task)
			- taskQueue.offer(task) 		//  maxPendingTasks == Integer.MAX_VALUE ? PlatformDependent.<Runnable>newMpscQueue()
		- if (!inEventLoop)
			- doStartThread()
				- executor.execute(new Runnable(){ SingleThreadEventExecutor.this.run(); })
					- NioEventLoop.run()	// for (;;) 永远在循环
						- SelectStrategy.SELECT:select(wakenUp.getAndSet(false))      // 1.选择感兴趣的事件
						 - selector
						- processSelectedKeys()										  // 2.处理选择的事件
						- runAllTasks(ioTime * (100 - ioRatio) / ioRatio);			  // 3.执行任务
						// ioRatio==100,执行任务无超时时间；否则在处理选择时间上按照比例处理
						

NioServerSocketChannel注册到EventLoop
MultithreadEventLoopGroup.register 
	- next().register(channel)
		- chooser.next()
			- PowerOfTwoEventExecutorChooser#next()
				- executors[idx.getAndIncrement() & executors.length - 1];	
				// 这里实际是取bossworker的EventLoop单例线程。需要任务队列，有很多任务需要进行调度，所以需要线程池的特性。
				// 但为了多线程的切换导致的性能损耗和为了消除同步，所以使用单个线程
	- ChannelFuture = register(Channel channel)
		- register(new DefaultChannelPromise(channel, this))      // DefaultChannelPromise类似于Future
			- SingleThreadEventLoop#register(ChannelPromise)
				- promise.channel().unsafe().register(this, promise);
					- eventLoop.inEventLoop()		
					// Netty 线程模型的高性能取决于对于当前执行的Thread 的身份的确定。
					// 如果不在当前线程，那么就需要很多同步措施（比如加锁），上下文切换等耗费性能的操作
					- register0(promise);
						- pipeline.invokeHandlerAddedIfNeeded();
							- DefaultChannelPipeline#invokeHandlerAddedIfNeeded
								- DefaultChannelPipeline#callHandlerAddedForAllHandlers
									- PendingHandlerAddedTask# execute()
										- DefaultChannelPipeline# callHandlerAdded0(final AbstractChannelHandlerContext ctx)
											- AbstractChannelHandlerContext# handler().handlerAdded(this)
						- pipeline.fireChannelRegistered();
	

[netty启动之后接受Client的请求]
NioEventLoop#processSelectedKey(SelectionKey, AbstractNioChannel)
	- if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0)	// 1.Selector不断接受到OP_ACCEPT(16)的连接请求
		- unsafe.read()
			- AbstractNioMessageChannel$NioMessageUnsafe#read()
				- localRead = doReadMessages(readBuf)
					// NioServerSocketChannal# 
					- SocketChannel ch = SocketUtils.accept(javaChannel());		// 2.NioServerSocketChannal创建NioSocketChannel
						- 
					- buf.add(new NioSocketChannel(this, ch));
				- pipeline.fireChannelRead(readBuf.get(i));;		            // 3.处理请求
					- DefaultChannelPipeline#fireChannelRead(Object msg)        // 执行管道中ChannelRead方法
						- invokeChannelRead(findContextInbound(), msg);		
							- do { ctx = ctx.next; } while (!ctx.inbound);		// 查找下一下inbound的
							- next.invokeChannelRead(m);						// 执行invokeChannelRead()
							- ((ChannelInboundHandler) handler()).channelRead(this, msg);	// handler的channelRead()
							- ctx.fireChannelRead(msg);							// 继续下传给next
Server注册NioServerSocketChannel后
其中 ServerBootstrapAcceptor 接受连接重新注册NioSocketChannel包括 handlerAdded-initChannel
	
	
[Handler添加]
 当 channel 绑定到 eventLoop 后(在这里是 NioServerSocketChannel 绑定到 bossGroup)中时, 
 会在 pipeline 中发出 fireChannelRegistered 事件, 接着就会触发 ChannelInitializer.initChannel 方法的调用.
在服务器 NioServerSocketChannel 的 pipeline 中添加的是 handler 与 ServerBootstrapAcceptor.
当有新的客户端连接请求时, ServerBootstrapAcceptor.channelRead 中负责新建此连接的 NioSocketChannel 并添加 childHandler 到 NioSocketChannel 对应的 pipeline 中, 
并将此 channel 绑定到 workerGroup 中的某个 eventLoop 中

handler 是在 accept 阶段起作用, 它处理客户端的连接请求.
childHandler 是在客户端连接建立以后起作用, 它负责客户端连接的 IO 交互.


-------------- ChannelPipeline & ChannelHandler & ChannelHandlerContext -----------------------
ChannelPipeline：任何一个 ChannelSocket 创建的同时都会创建 一个 pipeline
ChannelHandler：((ChannelInboundHandler) handler()).channelRead(this, msg) context对handler的封装
ChannelHandlerContext：当用户或系统内部调用 pipeline 的 add*** 方法添加 handler 时，都会创建一个包装这 handler 的 Context。

尾节点 TailContext - extends AbstractChannelHandlerContext implements ChannelInboundHandler  --> inbound=true;outbound=false
头结点 HeadContext - extends AbstractChannelHandlerContext implements ChannelOutboundHandler, ChannelInboundHandler  --> inbound=true;outbound=true
AbstractChannelHandlerContext --> ChannelHandlerContext
ChannelInboundHandler --> ChannelHandler				// head & tail本身也是 ChannelHandler 还是Context
ChannelPipeline是ChannelHandler的管理容器，负责ChannelHandler的查询，添加，替换和删除
ChannelPipeline 是线程安全的，ChannelHandler 是线程非安全
Inbound事件：如fireChannelRegistered, fireChannelActive, fireChannelRead...
	即某件事已经发生了, 然后通过 Inbound 事件进行通知
	head拿到connect之后pipeline().fireChannelActive()将通道激活(socket连接成功)。这fireXXX()也是Inbound事件的起点
Outbound事件：bind，connect，write，read...
    即请求某件事情的发生, 然后通过 Outbound 事件进行通知。Outbound 事件的传播方向是 tail -> customContext -> head.
	DefaultChannelPipeline#connect(SocketAddress,SocketAddress,ChannelPromise){ tail.connect(remoteAddress, localAddress, promise) }

Handler的ChannelRead()怎么被调用的？以ServerBootstrapAcceptor为例它是Server为了workGroup,childHandler()重新构建的一个ChannelInboundHandlerAdapter
  当一个 client 连接到 server 时, Java 底层的 NIO ServerSocketChannel 会有一个 SelectionKey.OP_ACCEPT 就绪事件, 接着就会调用到 NioServerSocketChannel.doReadMessages
  在 doReadMessages 中, 通过 javaChannel().accept() 获取到客户端新连接的 SocketChannel, 接着就实例化一个 NioSocketChannel, 
  并且传入 NioServerSocketChannel 对象(即 this), 由此可知, 我们创建的这个 NioSocketChannel 的父 Channel 就是 NioServerSocketChannel 实例 .
  接下来就经由 Netty 的 ChannelPipeline 机制, 将读取事件逐级发送到各个 handler 中, 于是就会触发前面我们提到的 ServerBootstrapAcceptor.channelRead 方法啦.
	

------------ read -------------
RecvByteBufAllocator.Handle allocHandle = unsafe().recvBufAllocHandle()  根据预测和计算最佳的缓存区确保不浪费





------------ ChannelOutboundBuffer --------






------------------------------------- POINT -------------------------------------------
[时间轮训器算法]
Worker						// 内部负责不断执行把待处理的timeout加入到槽位(最多10w)并执行当前槽位中过期的任务
HashedWheelTimeout  		// 每个任务的封装类, 链表结构, 负责保存deadline, remainingRounds等
HashedWheelBucket			// wheel数组元素, 负责存放HashedWheelTimeout链表
总体思路如下：
new WheelTimer的时候会初始化所有的槽位及内部的Worker
加入新的任务之后，如果是第一个任务则启动Worker并开始计算当前的槽位，把在timeout待处理的任务加入到相应的bucket中同时处理属性remainingRounds
当前的bucket在开始执行当前bucket时间之前的槽位任务，同时remainingRounds==0。即可表示时间已过

1.HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 16);
	- HashedWheelBucket[] wheel = createWheel(ticksPerWheel);     // 初始化槽位
	- mask = wheel.length - 1;
	- workerThread = threadFactory.newThread(worker);   	      // threadFactory开启一个Worker线程
	
2.hashedWheelTimer.newTimeout()   		        // 加入一个新的任务的时候
	-start()   									// 如果已经启动则break		
	  -Worker.run()							    
		- startTimeInitialized.countDown()
		- deadline = waitForNextTick() 			// 计算下个槽的执行时间并sleep, 返回就是Timer启动后到这次tick所过去的时间
		- int idx = (int) (tick & mask)
		- processCancelledTasks()
		- HashedWheelBucket bucket = wheel[idx]
		- transferTimeoutsToBuckets()			               // 将newTimeout()方法中加入到待处理定时任务队列中的任务加入到指定的格子中
			- long calculated = timeout.deadline / tickDuration;       		   // Queue<HashedWheelTimeout> timeouts = PlatformDependent.newMpscQueue();
			- timeout.remainingRounds = (calculated - tick) / wheel.length;    // 任务的轮数
			- int stopIndex = (int) (ticks & mask);
			- HashedWheelBucket bucket = wheel[stopIndex];     // 把待处理HashedWheelTimeout加入到当前的HashedWheelBucket  
			- bucket.addTimeout(timeout);
		- bucket.expireTimeouts(deadline)					  //
		- tick++
	  -startTimeInitialized.await();
	-HashedWheelTimeout timeout = new HashedWheelTimeout(this, task, deadline)
	-timeouts.add(timeout)

	
[心跳机制 IdleStateHandler]
public IdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) { ... }
readerIdleTimeSeconds, 读超时. 即当在指定的事件间隔内没有从 Channel 读取到数据时, 会触发一个 READER_IDLE 的 IdleStateEvent 事件.
writerIdleTimeSeconds, 写超时. 即当在指定的事件间隔内没有数据写入到 Channel 时, 会触发一个 WRITER_IDLE 的 IdleStateEvent 事件.
allIdleTimeSeconds, 读/写超时. 即当在指定的事件间隔内没有读或写操作时, 会触发一个 ALL_IDLE 的 IdleStateEvent 事件.
	
netty的心跳处理在弱网下怎么办 -- 弱网下可能出现发送时长久但依旧是在活跃的情况
netty的通讯协议是什么样的




#### Netty 20191212









































