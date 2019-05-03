#### IdleStateHandler netty的心跳检测事件
通常在 initChannel 方法中将 IdleStateHandler 添加到 pipeline 中；
然后在自己的 handler 中重写 userEventTriggered 方法当发生空闲事件（读或者写）就会触发这个方法，并传入具体事件。

TCP长连接优势在于：1.避免频繁的三次握手 2.避免TCP滑动窗口冷启动的低效问题
Netty在.option(ChannelOption.SO_KEEPALIVE, true)中利用Socket的底层设置SocketOptions.SO_KEEPALIVE属性。
   JDK关于此socket属性的大意是：当2个小时没有发生数据交换时，TCP会发送一个探针给对方，如果收到的是ACK标记的应答，则连接保持，否则关闭连接。