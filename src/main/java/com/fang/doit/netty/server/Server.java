package com.fang.doit.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @created 16/9/18 12:59
 */
public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler("INFO"))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        //p.addLast(new IdleStateHandler(0, 0, 5));
                        //p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
                        //p.addLast(new ServerHandler());
                        p.addLast(new EchoInServerHandler());
                    }
                });

            Channel ch = bootstrap.bind(8088).sync().channel();
            ch.closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}