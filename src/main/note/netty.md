### NIO
��IO������
> 1. Stream��Buffer, ������˳��ķ�ʽ��ȡһ�������ֽڶ�Buffer�ڴ�Channel�ж�ȡ���ݺ�Ϳ�������Ĳ���
> 2. �����������, ������read��ʱ��ǰ�߳��������ĵ���NIO��������
> 3. selector��·����ѡ����, Channelע�ᵽSelector֮��᲻��ѭ����ѯ(select)��Щע���Channel�Ƿ����Ѿ�����IO�¼���һ���̹߳�����Channel
  
  Channel:
1.FileChannel
2.SocketChannel
   SocketChannel sc = ((ServerSocketChannel) k.channel()).accept();
   HAConnection -- socketChannel.configureBlocking(false)...
   WriteSocketService -- this.socketChannel.register(this.selector, SelectionKey.OP_WRITE);   �ɼ�NIO��Server�õ�׼���õ�SocketChannel֮�󻹻����ע�ᴦ��

  Buffer:
1. ByteBuffer buf = ByteBuffer.allocate(48);                          //��java��ֱ�ӷ���
2. ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);	      // ֱ���ڴ����
3. this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize);   
��ô���2,3���㣿 �����MappedByteBuffer�Ѿ�ֱ��ӳ�䵽���̵�λ���˶������ڴ�ػ���Ҫ���ص�FileChannel���ܼ���flush?

  Selector:
  ��NIO��Selector.open()�ǵ�һ�̵߳�ѡ����(Netty���ж��̵߳�ѡ������ eventLoopGroupSelector ) ����һ���߳�ȥ���Ͻ���Channel�¼�
1.����HA ���ڷ����ÿ�ν��ܵ�Channel����ע�ᵽWrite/Readѡ�����С�
2.���һ��Channelע�ᵽѡ�������������Ƿ������� ��FileChannel������Ϊ����������
SelectionKey 
-- interest set����Ȥ���¼��� int interestSet = selectionKey.interestOps();
-- Channel & Selector

### BIO & NIO & AIO
> IO Block, ��ָ����ϵͳ��Ԥ�������Block�Ż�����Block�����統��ȡTCP��������ʱ�������Socket bufferû������ 

BIO ����read���������û�����Ѿ������ǰ�߳̾ͻ�Blockס
    �������Ϊ��ͬʱ��Ӧ����������������󣬱���ʵ��Ϊ���̵߳�.ÿ���̴߳���һ�����������߳������Ų�����������������
    1.�߳�Խ�࣬Context Switch��Խ�࣬��Context Switch��һ���Ƚ��صĲ���������ν�˷Ѵ�����CPU��
    2.ÿ���̻߳�ռ��һ�����ڴ���Ϊ�̵߳�ջ��������1000���߳�ͬʱ���У�ÿ��ռ��1MB�ڴ棬��ռ����1��G���ڴ档
NIO ����read���������û�����Ѿ�����ͻ����̷���-1, ����errno����ΪEAGAIN��
    ��ǰ�߳̿�����ȥ����������
    1.����д����ļ���������Ҫ�ȣ���ô�͵�һ��һ����read��������������Context Switch��read��ϵͳ���ã�ÿ����һ�ξ͵����û�̬�ͺ���̬�л�һ�Σ�
    2.��Ϣһ���ʱ�䲻�ð��ա�������Ҫ�¶��֮�����ݲ��ܵ����ȴ�ʱ�����̫����������Ӧ�ӳپ͹������̫�̣��ͻ���ɹ���Ƶ�������ԣ��ɺ�CPU���ѡ�
IO-Multiplexing ����ע��һ��socket�ļ�������������ϵͳ����ʾ "��Ҫ������Щfd�Ƿ���IO�¼����������˾͸��߳�����"
    a.IO��·������ָ�������������ͬһ��Socket(X)����ʵIO��·����˵���Ƕ��Socket��ֻ��������ϵͳ��һ��������ǵ��¼����ѡ�����HTTP2ȷʵ�Ƕ������������ͬһ��TCP����
    b.IO��·������NIO���������ǲ�Block��(X)��
    c.IO��·���ú�NIOһ�������IO(X)��
    ����ϵͳ�����ṩ��һЩ�ӿ���֧��IO��·���ã���õ���select��poll

    int select(int nfds, fd_set *readfds, fd_set *writefds, fd_set *exceptfds, struct timeval *timeout);
        1.����Ҫ�������¼��ŵ����飬select����fds�Ķ��block��ȡʱ���ʱselect�ᴦ������
        2.��һ���¼����� ����fds�������ĸ��¼�����
        3.��ȡ������
    ���㣺
        1.select�ܹ�֧�ֵ�����fd����ĳ�����1024�����Ҫ����߲�����web�������ǲ��ɽ��ܵ�
        2.fd���鰴�ռ������¼���Ϊ��3�����飬Ϊ����3������Ҫ����3���ڴ�ȥ���죬����ÿ�ε���selectǰ��Ҫ�������ǣ���Ϊselect�����3������)������select����3����Ҫ���û�̬����һ�ݵ��ں�̬���¼������Ҫ������3���顣�ܲ�ˬ
        3.select����״̬�ģ���ÿ�ε���select���ں˶�Ҫ���¼�����б�ע���fd��״̬��select���غ���Щ״̬�ͱ������ˣ��ں˲����ס���ǣ�������һ�ε��ã��ں���ȻҪ���¼��һ�顣���ǲ�ѯ��Ч�ʺܵ�

     int poll(struct pollfd *fds, nfds_t nfds, int timeout);
         ���������select ����֮��Ҳ������״̬����Ҫ��ѯ����

     int epfd = epoll_create(10);
     int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);

**epoll �� select&poll ǿ�ĵط�**
> 1. select��pollÿ�ζ���Ҫ����ɵ�fd�б��뵽�ںˣ���ʹ�ں�ÿ�α����ͷɨ�赽β����epoll��ȫ�Ƿ������ġ�
> 2. epoll���ں˵����ݱ���������֮��ÿ��ĳ����������fdһ�����¼��������ں˾�ֱ�ӱ��֮��
> 3. epoll_wait����ʱ���᳢��ֱ�Ӷ�ȡ����ʱ�Ѿ���Ǻõ�fd�б����û�оͻ����ȴ�״̬��
 
> 1. ˮƽ������Ĭ��ʵ�֣���ֻ�����ļ����������Ƿ���û��ɴ�������ݣ�����оͻ�һֱ���ڱ�������״̬��������ڶ�ȡhttp��������
ʱ����ֻ���ȡһ�������ж�ʱ�ͻ�һֱ��socket���������¼����š�
> 2. ��Ե������ֻ�����Ƿ����µ��¼���������ع�һ�β��ܳ����Ƿ�����ֻҪû���µ��¼�����epoll_wait��������Ϊ���fd������

��reactor�߳�ģ�͡�
Reactor��������Ӧ�¼������¼��ַ������˸��¼���Handler����
Handler���¼�������������ĳ���¼�������ִ�ж�Ӧ�¼���Task���¼����д���
Acceptor��Handler��һ�֣�����connect�¼�
���ͻ��˷���connect����ʱ��Reactor�Ὣaccept�¼��ַ���Acceptor����

				
��Netty��
 ----------------------- �ͻ��� ---------------------------------------
 Bootstrap bootstrap = new Bootstrap();
 this.eventLoopGroupWorker = new NioEventLoopGroup();
 Bootstrap handler = this.bootstrap.group(this.eventLoopGroupWorker)
			.channel(NioSocketChannel.class) 		// channel��selectorע���¼�֮�� ����Ķ�����Ը�channel�����ð�������&pipeline
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
   - MultithreadEventExecutorGroup(int nThreads, ThreadFactory threadFactory, Object... args)		// �����Ĭ�� NettyRuntime.availableProcessors() * 2
   
 ** connect() **
	- ChannelFuture = initAndRegister() 
			// channel()��ʼ����channel, 
		- 1.channel = channelFactory.newChannel()
			- NioSocketChannel 														// SocketChannel = provider.openSocketChannel()
				- AbstractNioByteChannel(Channel parent, SelectableChannel ch)		// OP_READ
					- AbstractNioChannel(parent, ch, SelectionKey.OP_READ)			// ch.configureBlocking(false); this.readInterestOp = OP_READ;
						- AbstractChannel(null)										// id = newId(); 
						  - unsafe = newUnsafe();  
						  - pipeline = newChannelPipeline();		//!! ��Channel��ʼ��ʱ���ȻChannelPipeline�ĳ�ʼ��(���Ϊ�ܵ��γ���������¼�Ҳ��Ӧ��ʼ��)
							- TailContext  ChannelInboundHandler
							- HeadContext  ChannelOutboundHandler
		- 2.init(channel)  		
			 - Bootstrap
				 - ChannelInitializer.channelRegistered(ChannelHandlerContext)		// ���Զ����handler��ӵ�ChannelPipeline��
				 - remove(ctx)														// ���remove��
			 - ServerBootstrap		// Server�ֳ�����Selectorһ�����ڽ���handler��һ������䵽Acceptor,
				- pipeline.addLast(handler);
					- newCtx = newContext(group, filterName(name, handler), handler);		// DefaultChannelHandlerContext
					- addLast0(newCtx)														// ���µ�Handler���뵽head tail��˫������
						- ChannelInitializer ����ʵ���� ChannelInboundHandler �ӿ�, inbound = true, outbound = false.
				- pipeline.addLast(new ServerBootstrapAcceptor(ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
					- channelRead(ChannelHandlerContext ctx, Object msg)
						- child.pipeline().addLast(childHandler);		// ���childHandler
						- childGroup.register(child).addListener		// ��channelע�ᵽworker
				
	    - 3.ChannelFuture = config().group().register(channel)		
			- next().register(channel)																//1.Channel���Ӧ��EventLoop����
				- AbstractUnsafe # register(EventLoop, ChannelPromise)
				- register0(ChannelPromise)					// 1.Channelע�ᵽSelector
				    - AbstractNioChannel#doRegister
						- selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this)		//2.���ײ�� Java NIO SocketChannel ע�ᵽָ���� selector ��
					- pipeline.fireChannelRegistered();		// 2.����Զ���handler ������
						- AbstractChannelHandlerContext#invokeChannelRegistered(head)  head = AbstractChannelHandlerContext
					

	- Bootstrap.doConnect(future.getNow(), localAddress, promise)
			- channel.eventLoop().execute()
				- ChannelFuture = AbstractChannel.connect(SocketAddress, ChannelPromise)
					- DefaultChannelPipeline.connect(remoteAddress, promise)
						- (AbstractChannelHandlerContext) tail.connect(remoteAddress, promise);	
							- AbstractChannelHandlerContext next = findContextOutbound()  			// DefaultChannelPipeline �ڵ�˫������� tail ��ʼ, ������ǰѰ�ҵ�һ�� outbound Ϊ true �� AbstractChannelHandlerContext, Ȼ��������� invokeConnect ����
							- next.invokeConnect(remoteAddress, localAddress, promise);
					
-------------------------- ����� ----------------------------------
 this.serverBootstrap = new ServerBootstrap();
			// NioEvenLoop�ۺ��˶�·������Selector����ͬʱ��������ɰ���ǧ���ͻ���Channel
            this.serverBootstrap.group(this.eventLoopGroupBoss, this.eventLoopGroupSelector)	
				// ����ReflectiveChannelFactory��bootstrap, ������ɵ�newChannel����class
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

[Server��Client��һ����Channel]
bossGroup   -- NioServerSocketChannal 
workerGroup(childGroup) -- ��childHandler
	- ServerBootstrapAcceptor.channelRead(ChannelHandlerContext, Object msg)		
		- Channel child = (Channel) msg
		- child.pipeline().addLast(childHandler)
		- childGroup.register(child)
		
��һ��client���ӵ�serverʱ, Java�ײ��NIO ServerSocketChannel����һ��SelectionKey.OP_ACCEPT�����¼�, ���žͻ���õ� NioServerSocketChannel.doReadMessages
NioServerSocketChannel.doReadMessages(List<Object> buf)
	- SocketChannel ch = SocketUtils.accept(javaChannel());    // ��ȡ�ͻ��˵�������
	- buf.add(new NioSocketChannel(this, ch));

ChannelPipeline pipeline = ch.pipeline()
pipeline.addLast(handler)		// Ϊpipeline��handler
	- DefaultChannelPipeline#addLast(ChannelHandler... handlers)
		- addLast(EventExecutorGroup group, String name, ChannelHandler handler)
			- AbstractChannelHandlerContext newCtx = newContext(group, filterName(name, handler), handler);
			- callHandlerAdded0(newCtx);
				- ctx.callHandlerAdded();
					- AbstractChannelHandlerContext#callHandlerAdded()
						- ChannelInitializer#handlerAdded(ChannelHandlerContext ctx)
							- initChannel(ChannelHandlerContext ctx)
								- initChannel((C) ctx.channel());			// 
	
== [ NioEventLoop �� NioServerSocketChannel ��ע��]
NioEventLoop(selector, selectedKeys..)��ȡ����Ϣ֮��ֱ�ӵ���ChannelPipeline��fireChannelRead.
   ֻҪ�û��������л��߳�һֱ������NioEventLoop�����û���Handler�ڼ䲻�����л��������л���ʽ�����˶��߳̾������µ�������
EventLoop��һ�������̳߳أ�ÿ��EventLoop���԰󶨶��Channel��ÿ��Channelֻ����һ��EventLoop������
EventLoop ��������һ����ѭ���������ѭ������3�����飺
1.�������ĵȴ� Nio �¼�
2.���� Nio �¼�
3.������Ϣ�����е�����
AbstractChannel.register(EventLoop, ChannelPromise)  //
	- SingleThreadEventExecutor.execute(Runnable){ ... }
		- addTask(task)
			- taskQueue.offer(task) 		//  maxPendingTasks == Integer.MAX_VALUE ? PlatformDependent.<Runnable>newMpscQueue()
		- if (!inEventLoop)
			- doStartThread()
				- executor.execute(new Runnable(){ SingleThreadEventExecutor.this.run(); })
					- NioEventLoop.run()	// for (;;) ��Զ��ѭ��
						- SelectStrategy.SELECT:select(wakenUp.getAndSet(false))      // 1.ѡ�����Ȥ���¼�
						 - selector
						- processSelectedKeys()										  // 2.����ѡ����¼�
						- runAllTasks(ioTime * (100 - ioRatio) / ioRatio);			  // 3.ִ������
						// ioRatio==100,ִ�������޳�ʱʱ�䣻�����ڴ���ѡ��ʱ���ϰ��ձ�������
						

NioServerSocketChannelע�ᵽEventLoop
MultithreadEventLoopGroup.register 
	- next().register(channel)
		- chooser.next()
			- PowerOfTwoEventExecutorChooser#next()
				- executors[idx.getAndIncrement() & executors.length - 1];	
				// ����ʵ����ȡbossworker��EventLoop�����̡߳���Ҫ������У��кܶ�������Ҫ���е��ȣ�������Ҫ�̳߳ص����ԡ�
				// ��Ϊ�˶��̵߳��л����µ�������ĺ�Ϊ������ͬ��������ʹ�õ����߳�
	- ChannelFuture = register(Channel channel)
		- register(new DefaultChannelPromise(channel, this))      // DefaultChannelPromise������Future
			- SingleThreadEventLoop#register(ChannelPromise)
				- promise.channel().unsafe().register(this, promise);
					- eventLoop.inEventLoop()		
					// Netty �߳�ģ�͵ĸ�����ȡ���ڶ��ڵ�ǰִ�е�Thread ����ݵ�ȷ����
					// ������ڵ�ǰ�̣߳���ô����Ҫ�ܶ�ͬ����ʩ��������������������л��Ⱥķ����ܵĲ���
					- register0(promise);
						- pipeline.invokeHandlerAddedIfNeeded();
							- DefaultChannelPipeline#invokeHandlerAddedIfNeeded
								- DefaultChannelPipeline#callHandlerAddedForAllHandlers
									- PendingHandlerAddedTask# execute()
										- DefaultChannelPipeline# callHandlerAdded0(final AbstractChannelHandlerContext ctx)
											- AbstractChannelHandlerContext# handler().handlerAdded(this)
						- pipeline.fireChannelRegistered();
	

[netty����֮�����Client������]
NioEventLoop#processSelectedKey(SelectionKey, AbstractNioChannel)
	- if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0)	// 1.Selector���Ͻ��ܵ�OP_ACCEPT(16)����������
		- unsafe.read()
			- AbstractNioMessageChannel$NioMessageUnsafe#read()
				- localRead = doReadMessages(readBuf)
					// NioServerSocketChannal# 
					- SocketChannel ch = SocketUtils.accept(javaChannel());		// 2.NioServerSocketChannal����NioSocketChannel
						- 
					- buf.add(new NioSocketChannel(this, ch));
				- pipeline.fireChannelRead(readBuf.get(i));;		            // 3.��������
					- DefaultChannelPipeline#fireChannelRead(Object msg)        // ִ�йܵ���ChannelRead����
						- invokeChannelRead(findContextInbound(), msg);		
							- do { ctx = ctx.next; } while (!ctx.inbound);		// ������һ��inbound��
							- next.invokeChannelRead(m);						// ִ��invokeChannelRead()
							- ((ChannelInboundHandler) handler()).channelRead(this, msg);	// handler��channelRead()
							- ctx.fireChannelRead(msg);							// �����´���next
Serverע��NioServerSocketChannel��
���� ServerBootstrapAcceptor ������������ע��NioSocketChannel���� handlerAdded-initChannel
	
	
[Handler���]
 �� channel �󶨵� eventLoop ��(�������� NioServerSocketChannel �󶨵� bossGroup)��ʱ, 
 ���� pipeline �з��� fireChannelRegistered �¼�, ���žͻᴥ�� ChannelInitializer.initChannel �����ĵ���.
�ڷ����� NioServerSocketChannel �� pipeline ����ӵ��� handler �� ServerBootstrapAcceptor.
�����µĿͻ�����������ʱ, ServerBootstrapAcceptor.channelRead �и����½������ӵ� NioSocketChannel ����� childHandler �� NioSocketChannel ��Ӧ�� pipeline ��, 
������ channel �󶨵� workerGroup �е�ĳ�� eventLoop ��

handler ���� accept �׶�������, ������ͻ��˵���������.
childHandler ���ڿͻ������ӽ����Ժ�������, ������ͻ������ӵ� IO ����.


-------------- ChannelPipeline & ChannelHandler & ChannelHandlerContext -----------------------
ChannelPipeline���κ�һ�� ChannelSocket ������ͬʱ���ᴴ�� һ�� pipeline
ChannelHandler��((ChannelInboundHandler) handler()).channelRead(this, msg) context��handler�ķ�װ
ChannelHandlerContext�����û���ϵͳ�ڲ����� pipeline �� add*** ������� handler ʱ�����ᴴ��һ����װ�� handler �� Context��

β�ڵ� TailContext - extends AbstractChannelHandlerContext implements ChannelInboundHandler  --> inbound=true;outbound=false
ͷ��� HeadContext - extends AbstractChannelHandlerContext implements ChannelOutboundHandler, ChannelInboundHandler  --> inbound=true;outbound=true
AbstractChannelHandlerContext --> ChannelHandlerContext
ChannelInboundHandler --> ChannelHandler				// head & tail����Ҳ�� ChannelHandler ����Context
ChannelPipeline��ChannelHandler�Ĺ�������������ChannelHandler�Ĳ�ѯ����ӣ��滻��ɾ��
ChannelPipeline ���̰߳�ȫ�ģ�ChannelHandler ���̷߳ǰ�ȫ
Inbound�¼�����fireChannelRegistered, fireChannelActive, fireChannelRead...
	��ĳ�����Ѿ�������, Ȼ��ͨ�� Inbound �¼�����֪ͨ
	head�õ�connect֮��pipeline().fireChannelActive()��ͨ������(socket���ӳɹ�)����fireXXX()Ҳ��Inbound�¼������
Outbound�¼���bind��connect��write��read...
    ������ĳ������ķ���, Ȼ��ͨ�� Outbound �¼�����֪ͨ��Outbound �¼��Ĵ��������� tail -> customContext -> head.
	DefaultChannelPipeline#connect(SocketAddress,SocketAddress,ChannelPromise){ tail.connect(remoteAddress, localAddress, promise) }

Handler��ChannelRead()��ô�����õģ���ServerBootstrapAcceptorΪ������ServerΪ��workGroup,childHandler()���¹�����һ��ChannelInboundHandlerAdapter
  ��һ�� client ���ӵ� server ʱ, Java �ײ�� NIO ServerSocketChannel ����һ�� SelectionKey.OP_ACCEPT �����¼�, ���žͻ���õ� NioServerSocketChannel.doReadMessages
  �� doReadMessages ��, ͨ�� javaChannel().accept() ��ȡ���ͻ��������ӵ� SocketChannel, ���ž�ʵ����һ�� NioSocketChannel, 
  ���Ҵ��� NioServerSocketChannel ����(�� this), �ɴ˿�֪, ���Ǵ�������� NioSocketChannel �ĸ� Channel ���� NioServerSocketChannel ʵ�� .
  �������;��� Netty �� ChannelPipeline ����, ����ȡ�¼��𼶷��͵����� handler ��, ���Ǿͻᴥ��ǰ�������ᵽ�� ServerBootstrapAcceptor.channelRead ������.
	

------------ read -------------
RecvByteBufAllocator.Handle allocHandle = unsafe().recvBufAllocHandle()  ����Ԥ��ͼ�����ѵĻ�����ȷ�����˷�





------------ ChannelOutboundBuffer --------






------------------------------------- POINT -------------------------------------------
[ʱ����ѵ���㷨]
Worker						// �ڲ����𲻶�ִ�аѴ������timeout���뵽��λ(���10w)��ִ�е�ǰ��λ�й��ڵ�����
HashedWheelTimeout  		// ÿ������ķ�װ��, ����ṹ, ���𱣴�deadline, remainingRounds��
HashedWheelBucket			// wheel����Ԫ��, ������HashedWheelTimeout����
����˼·���£�
new WheelTimer��ʱ����ʼ�����еĲ�λ���ڲ���Worker
�����µ�����֮������ǵ�һ������������Worker����ʼ���㵱ǰ�Ĳ�λ������timeout�������������뵽��Ӧ��bucket��ͬʱ��������remainingRounds
��ǰ��bucket�ڿ�ʼִ�е�ǰbucketʱ��֮ǰ�Ĳ�λ����ͬʱremainingRounds==0�����ɱ�ʾʱ���ѹ�

1.HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 16);
	- HashedWheelBucket[] wheel = createWheel(ticksPerWheel);     // ��ʼ����λ
	- mask = wheel.length - 1;
	- workerThread = threadFactory.newThread(worker);   	      // threadFactory����һ��Worker�߳�
	
2.hashedWheelTimer.newTimeout()   		        // ����һ���µ������ʱ��
	-start()   									// ����Ѿ�������break		
	  -Worker.run()							    
		- startTimeInitialized.countDown()
		- deadline = waitForNextTick() 			// �����¸��۵�ִ��ʱ�䲢sleep, ���ؾ���Timer���������tick����ȥ��ʱ��
		- int idx = (int) (tick & mask)
		- processCancelledTasks()
		- HashedWheelBucket bucket = wheel[idx]
		- transferTimeoutsToBuckets()			               // ��newTimeout()�����м��뵽������ʱ��������е�������뵽ָ���ĸ�����
			- long calculated = timeout.deadline / tickDuration;       		   // Queue<HashedWheelTimeout> timeouts = PlatformDependent.newMpscQueue();
			- timeout.remainingRounds = (calculated - tick) / wheel.length;    // ���������
			- int stopIndex = (int) (ticks & mask);
			- HashedWheelBucket bucket = wheel[stopIndex];     // �Ѵ�����HashedWheelTimeout���뵽��ǰ��HashedWheelBucket  
			- bucket.addTimeout(timeout);
		- bucket.expireTimeouts(deadline)					  //
		- tick++
	  -startTimeInitialized.await();
	-HashedWheelTimeout timeout = new HashedWheelTimeout(this, task, deadline)
	-timeouts.add(timeout)

	
[�������� IdleStateHandler]
public IdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) { ... }
readerIdleTimeSeconds, ����ʱ. ������ָ�����¼������û�д� Channel ��ȡ������ʱ, �ᴥ��һ�� READER_IDLE �� IdleStateEvent �¼�.
writerIdleTimeSeconds, д��ʱ. ������ָ�����¼������û������д�뵽 Channel ʱ, �ᴥ��һ�� WRITER_IDLE �� IdleStateEvent �¼�.
allIdleTimeSeconds, ��/д��ʱ. ������ָ�����¼������û�ж���д����ʱ, �ᴥ��һ�� ALL_IDLE �� IdleStateEvent �¼�.
	
netty��������������������ô�� -- �����¿��ܳ��ַ���ʱ���õ��������ڻ�Ծ�����
netty��ͨѶЭ����ʲô����




#### Netty 20191212









































