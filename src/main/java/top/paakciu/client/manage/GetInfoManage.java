package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Handler;

/**
 * @author paakciu
 * @ClassName: GetInfoManage
 * @date: 2021/4/15 19:04
 */
public class GetInfoManage extends BaseManageWith2Function<GetInfoManage>{
    protected SimpleChannelInboundHandler handler;
    protected GetInfoManage(Channel channel, SimpleChannelInboundHandler handler) {
        super(channel);
        this.handler=handler;
        setSon(this);
    }

}
