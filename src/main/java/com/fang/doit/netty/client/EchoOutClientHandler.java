package com.fang.doit.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/4/20 14:27
 */
public class EchoOutClientHandler extends ChannelOutboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        logger.info("client bind");
        super.bind(ctx, localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
                        ChannelPromise promise) throws Exception {
        logger.info("client connect");
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        logger.info("client disconnect");
        super.disconnect(ctx, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        logger.info("client close");
        super.close(ctx, promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        logger.info("client deregister");
        super.deregister(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        logger.info("client read");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("client write");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        logger.info("client flush");
        super.flush(ctx);
    }
}
