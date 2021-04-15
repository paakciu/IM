package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.client.listener.HandlerListener;
import top.paakciu.protocal.packet.GetInfoResponsePacket;
import top.paakciu.utils.ExtraPacketHelper;
import top.paakciu.utils.info.ChannelUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetInfoResponseHandler
 * @date: 2021/4/15 18:51
 */
public class GetInfoResponseHandler extends SimpleChannelInboundHandlerWith2Function<GetInfoResponsePacket> {
    HandlerListener<ChannelUser> ChannelUser_Listener=null;
    HandlerListener<List<ChannelUser>> All_Friends_listener=null;
    HandlerListener<List<ChannelUser>> OnLine_Friends_listener=null;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetInfoResponsePacket msg) throws Exception {
        switch (msg.getType()){
            case 1:
                if(ChannelUser_Listener!=null){
                    ChannelUser_Listener.onHandle((ChannelUser) msg.getObj());
                }
                return;

            case 2:
                if(All_Friends_listener!=null){
                    All_Friends_listener.onHandle((List<ChannelUser>) msg.getObj());
                    //ExtraPacketHelper.BytesToObject(List.class)
                }
                return;
            case 3:
                if(OnLine_Friends_listener!=null){
                    OnLine_Friends_listener.onHandle((List<ChannelUser>) msg.getObj());
                    //ExtraPacketHelper.BytesToObject(List.class)
                }
                return;
                //TODO 做到这儿
        }
    }
}
