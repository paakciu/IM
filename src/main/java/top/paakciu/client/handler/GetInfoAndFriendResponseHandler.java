package top.paakciu.client.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import top.paakciu.client.listener.HandlerListener;
import top.paakciu.protocal.packet.GetInfoAndFriendResponsePacket;
import top.paakciu.utils.ExtraPacketHelper;
import top.paakciu.utils.info.ChannelUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetInfoAndFriendResponseHandler
 * @date: 2021/4/15 18:51
 */
public class GetInfoAndFriendResponseHandler extends SimpleChannelInboundHandlerWith2Function<GetInfoAndFriendResponsePacket> {
    HandlerListener<ChannelUser> ChannelUser_Listener=null;
    HandlerListener<List<ChannelUser>> Add_Friends_listener=null;
    HandlerListener<List<ChannelUser>> Delete_Friends_listener=null;
    HandlerListener<List<ChannelUser>> All_Friends_listener=null;
    HandlerListener<List<ChannelUser>> OnLine_Friends_listener=null;

    public void setAdd_Friends_listener(HandlerListener<List<ChannelUser>> add_Friends_listener) {
        Add_Friends_listener = add_Friends_listener;
    }

    public void setDelete_Friends_listener(HandlerListener<List<ChannelUser>> delete_Friends_listener) {
        Delete_Friends_listener = delete_Friends_listener;
    }

    public void setChannelUser_Listener(HandlerListener<ChannelUser> channelUser_Listener) {
        ChannelUser_Listener = channelUser_Listener;
    }

    public void setAll_Friends_listener(HandlerListener<List<ChannelUser>> all_Friends_listener) {
        All_Friends_listener = all_Friends_listener;
    }

    public void setOnLine_Friends_listener(HandlerListener<List<ChannelUser>> onLine_Friends_listener) {
        OnLine_Friends_listener = onLine_Friends_listener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetInfoAndFriendResponsePacket msg) throws Exception {
        switch (msg.getType()){
            case 1:
                if(ChannelUser_Listener!=null){
                    ChannelUser_Listener.onHandle(ExtraPacketHelper.StringToObject(ChannelUser.class,msg.getObj()));
                }
                return;
            case 2:{
                //添加好友--双向
                if(Add_Friends_listener!=null)
                    Add_Friends_listener.onHandle(ExtraPacketHelper.StringToArray(ChannelUser.class,msg.getObj()));
                return;
            }
            case 3:{
                //删除好友--双向
                if(Delete_Friends_listener!=null)
                    Delete_Friends_listener.onHandle(ExtraPacketHelper.StringToArray(ChannelUser.class,msg.getObj()));
                return;
            }
            case 4:
                if(All_Friends_listener!=null){
                    All_Friends_listener.onHandle(ExtraPacketHelper.StringToArray(ChannelUser.class,msg.getObj()));
                    //ExtraPacketHelper.BytesToObject(List.class)
                }
                return;
            case 5:
                if(OnLine_Friends_listener!=null){
                    OnLine_Friends_listener.onHandle(ExtraPacketHelper.StringToArray(ChannelUser.class,msg.getObj()));
                    //ExtraPacketHelper.BytesToObject(List.class)
                }
                return;
            default:
                return;
        }
    }
}
