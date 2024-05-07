package com.fang.doit.netty.client;

import com.fang.doit.netty.common.CustomHeartbeatHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @created 16/9/18 12:59
 */
public class Client {
    private NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
    private Channel channel;
    private Bootstrap bootstrap;

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.start();
        client.sendData();
    }

    public void sendData() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            if (channel != null && channel.isActive()) {
                String content = "client msg " + i;
                ByteBuf buf = channel.alloc().buffer(5 + content.getBytes().length);
                buf.writeInt(5 + content.getBytes().length);
                buf.writeByte(CustomHeartbeatHandler.CUSTOM_MSG);
                buf.writeBytes(content.getBytes());
                channel.writeAndFlush(buf);
            }

            Thread.sleep(random.nextInt(20000));
        }
    }

    public void start() {
        try {
            bootstrap = new Bootstrap();
            bootstrap
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            //// 如果客户端在间隔5s后都没收到Server的消息或向Server发送消息则产生ALL_IDLE事件 不能全部为0
                            //p.addLast(new IdleStateHandler(0, 0, 5));
                            //p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, -4, 0));
                            //p.addLast(new ClientHandler(Client.this));
                            p.addLast(new EchoOutClientHandler());
                        }
                    });
            doConnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect("127.0.0.1", 8088);

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();
                    System.out.println("Connect to server successfully!");
                } else {
                    System.out.println("Failed to connect to server, try connect after 10s");

                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }

}
