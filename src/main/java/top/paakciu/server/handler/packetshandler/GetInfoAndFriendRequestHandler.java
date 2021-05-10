package top.paakciu.server.handler.packetshandler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.handler.GetInfoAndFriendResponseHandler;
import top.paakciu.mbg.model.Friend;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.GetInfoAndFriendRequestPacket;
import top.paakciu.protocal.packet.GetInfoAndFriendResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.FriendsService;
import top.paakciu.service.UserService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ExtraPacketHelper;
import top.paakciu.utils.info.ChannelUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetInfoAndFriendRequestHandler
 * @date: 2021/4/15 19:49
 */
@ChannelHandler.Sharable
public class GetInfoAndFriendRequestHandler extends SimpleChannelInboundHandler<GetInfoAndFriendRequestPacket> {
    public static final GetInfoAndFriendRequestHandler INSTANCE=new GetInfoAndFriendRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetInfoAndFriendRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            System.out.println("收到GetInfoAndFriendRequestPacket"+msg.toString());
            switch (msg.getType()){
                case 1:{
                    //根据id获取信息-例如名字
                    Long userId=msg.getUserId();
                    User user = UserService.getUserById(userId);
                    ChannelUser channelUser=null;
                    if(user!=null)
                        channelUser = new ChannelUser(user.getId(),user.getUsername());
                    GetInfoAndFriendResponsePacket responsePacket=new GetInfoAndFriendResponsePacket();
                    responsePacket.setType(1);
                    responsePacket.setObj(ExtraPacketHelper.ObjectToString(channelUser));
                    ctx.writeAndFlush(responsePacket);
                    return;
                }
                case 2:{
                    //添加好友--双向
                    Long userId1= msg.getUserId();
                    Long userId2=ExtraPacketHelper.StringToObject(Long.class,msg.getObj());
                    if(userId2!=null&&userId1!=null){
                        Integer ref=FriendsService.addFriends(userId1,userId2);
                        if(ref==null)
                            handleError(ctx.channel(), "添加好友持久化失败,");
                        else if(ref==0)
                            handleError(ctx.channel(), "好友已经添加，请勿重复添加");
                        else {
                            GetInfoAndFriendResponsePacket responsePacket=new GetInfoAndFriendResponsePacket();
                            responsePacket.setType(2);

                            ChannelUser channelUser1=null;
                            ChannelUser channelUser2=null;
                            User user1 = UserService.getUserById(userId1);
                            User user2 = UserService.getUserById(userId2);
                            if(user1==null||user2==null){
                                handleError(ctx.channel(), "出问题了user1、2");
                                return;
                            }
                            channelUser1=new ChannelUser(user1.getId(),user1.getUsername());
                            channelUser2=new ChannelUser(user2.getId(),user2.getUsername());
                            List<ChannelUser> list=new ArrayList<>();
                            if(channelUser1!=null)list.add(channelUser1);
                            if(channelUser2!=null)list.add(channelUser2);
                            responsePacket.setObj(ExtraPacketHelper.ObjectToString(list));
                            ctx.writeAndFlush(responsePacket);
                        }
                    }
                    return;
                }
                case 3:{
                    //删除好友--双向
                    Long userId1= msg.getUserId();
                    Long userId2=ExtraPacketHelper.StringToObject(Long.class,msg.getObj());
                    if(userId2!=null&&userId1!=null){
                        Integer ref=FriendsService.deleteFriends(userId1,userId2);
                        if(ref==null)
                            handleError(ctx.channel(), "删除好友持久化失败");
                    }
                    GetInfoAndFriendResponsePacket responsePacket=new GetInfoAndFriendResponsePacket();
                    responsePacket.setType(3);
//                    responsePacket.setObj(true);
//                    ctx.writeAndFlush(responsePacket);
                    ChannelUser channelUser1=null;
                    ChannelUser channelUser2=null;
                    User user1 = UserService.getUserById(userId1);
                    User user2 = UserService.getUserById(userId2);
                    if(user1==null||user2==null){
                        handleError(ctx.channel(), "出问题了user1、2");
                        return;
                    }
                    channelUser1=new ChannelUser(user1.getId(),user1.getUsername());
                    channelUser2=new ChannelUser(user2.getId(),user2.getUsername());
                    List<ChannelUser> list=new ArrayList<>();
                    if(channelUser1!=null)list.add(channelUser1);
                    if(channelUser2!=null)list.add(channelUser2);
                    responsePacket.setObj(ExtraPacketHelper.ObjectToString(list));

                    ctx.writeAndFlush(responsePacket);
                    return;
                }
                case 4:{
                    //获取好友列表
                    Long userId=msg.getUserId();
                    if(userId!=null){
                        List<Friend> list=FriendsService.getFriends(userId);
                        if(list!=null){
                            List<ChannelUser> userList=new ArrayList<>();
                            for (Friend friend : list) {
                                User user=UserService.getUserById(friend.getFrienduserid());
                                if(user!=null)
                                    userList.add(new ChannelUser(user.getId(),user.getUsername()));
                            }
                            GetInfoAndFriendResponsePacket responsePacket=new GetInfoAndFriendResponsePacket();
                            responsePacket.setType(4);
                            responsePacket.setObj(ExtraPacketHelper.ObjectToString(userList));
                            ctx.writeAndFlush(responsePacket);
                            return;
                        }else{
                            handleError(ctx.channel(), "获取所有好友失败");
                        }
                    }
                    return;
                }
                case 5:{
                    //获取在线好友列表
                    Long userId=msg.getUserId();
                    if(userId!=null){
                        List<Friend> list=FriendsService.getFriends(userId);
                        if(list!=null){
                            List<ChannelUser> userList=new ArrayList<>();
                            for (Friend friend : list) {
                                Channel channel=AttributesHelper.getChannel(friend.getFrienduserid());
                                if(channel!=null){
                                    ChannelUser channelUser= AttributesHelper.getChannelUser(channel);
                                    if(channelUser!=null){
                                        userList.add(channelUser);
                                    }
                                }

                            }
                            GetInfoAndFriendResponsePacket responsePacket=new GetInfoAndFriendResponsePacket();
                            responsePacket.setType(5);
                            responsePacket.setObj(ExtraPacketHelper.ObjectToString(userList));
                            ctx.writeAndFlush(responsePacket);
                            return;
                        }else{
                            handleError(ctx.channel(), "获取在线好友失败");
                        }
                    }
                    return;
                }
                default:
                    return;
            }
        });
    }
    public void handleError(Channel channel, String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.GET_INFO_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
