package com.lee.IMnetty.server.hanldlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * TestIdleHandler
 *
 * @author Lee
 * @date 2020/1/29
 */
public class TestIdleHandler extends IdleStateHandler {

    public TestIdleHandler() {
        super(15, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("连接假死，直接断开吧");
        ctx.channel().close();
//        super.channelIdle(ctx, evt);
    }
}
