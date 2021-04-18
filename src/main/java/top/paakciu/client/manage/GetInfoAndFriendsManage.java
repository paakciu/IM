package top.paakciu.client.manage;

import io.netty.channel.Channel;
import top.paakciu.client.handler.GetInfoAndFriendResponseHandler;
import top.paakciu.client.listener.HandlerListener;
import top.paakciu.protocal.packet.GetInfoAndFriendRequestPacket;
import top.paakciu.utils.ExtraPacketHelper;
import top.paakciu.utils.info.ChannelUser;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetInfoAndFriendsManage
 * @date: 2021/4/15 19:04
 */
public class GetInfoAndFriendsManage extends BaseManageWith2Function<GetInfoAndFriendsManage>{
    protected GetInfoAndFriendResponseHandler handler;
    public GetInfoAndFriendsManage(Channel channel, GetInfoAndFriendResponseHandler handler) {
        super(channel);
        this.handler=handler;
        setSon(this);
    }


    public GetInfoAndFriendsManage getUserInfo(Long userId){
        GetInfoAndFriendRequestPacket GetInfoAndFriendRequestPacket=new GetInfoAndFriendRequestPacket();
        GetInfoAndFriendRequestPacket.setUserId(userId);
        GetInfoAndFriendRequestPacket.setType(1);
        writeAndFlushAddListener(GetInfoAndFriendRequestPacket);
        return this;
    }

    public GetInfoAndFriendsManage setGetUserInfoListener(HandlerListener<ChannelUser> listener){
        handler.setChannelUser_Listener(listener);
        return this;
    }



    public GetInfoAndFriendsManage addFriends(Long userId,Long userId2){
        GetInfoAndFriendRequestPacket GetInfoAndFriendRequestPacket=new GetInfoAndFriendRequestPacket();
        GetInfoAndFriendRequestPacket.setUserId(userId);
        GetInfoAndFriendRequestPacket.setObj(ExtraPacketHelper.ObjectToString(userId2));
        GetInfoAndFriendRequestPacket.setType(2);
        writeAndFlushAddListener(GetInfoAndFriendRequestPacket);
        return this;
    }

    public GetInfoAndFriendsManage setAddFriendslistener(HandlerListener<List<ChannelUser>> add_Friends_listener) {
        handler.setAdd_Friends_listener(add_Friends_listener);
        return this;
    }



    public GetInfoAndFriendsManage deleteFriends(Long userId,Long userId2){
        GetInfoAndFriendRequestPacket GetInfoAndFriendRequestPacket=new GetInfoAndFriendRequestPacket();
        GetInfoAndFriendRequestPacket.setUserId(userId);
        GetInfoAndFriendRequestPacket.setObj(ExtraPacketHelper.ObjectToString(userId2));
        GetInfoAndFriendRequestPacket.setType(3);
        writeAndFlushAddListener(GetInfoAndFriendRequestPacket);
        return this;
    }

    public GetInfoAndFriendsManage setDeleteFriendslistener(HandlerListener<List<ChannelUser>> delete_Friends_listener) {
        handler.setDelete_Friends_listener(delete_Friends_listener);
        return this;
    }



    public GetInfoAndFriendsManage getAllFriends(Long userId){
        GetInfoAndFriendRequestPacket GetInfoAndFriendRequestPacket=new GetInfoAndFriendRequestPacket();
        GetInfoAndFriendRequestPacket.setUserId(userId);
        GetInfoAndFriendRequestPacket.setType(4);
        writeAndFlushAddListener(GetInfoAndFriendRequestPacket);
        return this;
    }
    public GetInfoAndFriendsManage setAllFriendslistener(HandlerListener<List<ChannelUser>> all_Friends_listener) {
        handler.setAll_Friends_listener(all_Friends_listener);
        return this;
    }



    public GetInfoAndFriendsManage getOnLineFriends(Long userId){
        GetInfoAndFriendRequestPacket GetInfoAndFriendRequestPacket=new GetInfoAndFriendRequestPacket();
        GetInfoAndFriendRequestPacket.setUserId(userId);
        GetInfoAndFriendRequestPacket.setType(5);
        writeAndFlushAddListener(GetInfoAndFriendRequestPacket);
        return this;
    }
    public GetInfoAndFriendsManage setOnLineFriendslistener(HandlerListener<List<ChannelUser>> onLine_Friends_listener) {
        handler.setOnLine_Friends_listener(onLine_Friends_listener);
        return this;
    }

}
