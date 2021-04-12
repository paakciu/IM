package top.paakciu.client.manage;

import io.netty.channel.Channel;
import top.paakciu.protocal.packet.PullMessageRequestPacket;

/**
 * @author paakciu
 * @ClassName: PullMessageManage
 * @date: 2021/4/12 14:59
 */
public class PullMessageManage extends BaseManageWith2Function<PullMessageManage>{
    public PullMessageManage(Channel channel) {
        super(channel);
        setSon(this);
    }
//    @Deprecated
//    public PullMessageManage pullMessageManage(){
//        PullMessageRequestPacket pullMessageRequestPacket=new PullMessageRequestPacket();
//        //TODO 参数
//        //pullMessageRequestPacket;
//        writeAndFlushAddListener(pullMessageRequestPacket);
//        return this;
//    }

    public PullMessageManage pullMessageSingleByFromMessageId(long msgid,long id1,long id2,boolean isBigger){
        PullMessageRequestPacket pullMessageRequestPacket=new PullMessageRequestPacket();
        pullMessageRequestPacket.setAsSingleByFromMessageId(msgid,id1,id2,isBigger);
        //System.out.println("发送包的信息是："+pullMessageRequestPacket);
        writeAndFlushAddListener(pullMessageRequestPacket);
//        channel.writeAndFlush(pullMessageRequestPacket);
        return this;
    }
    //setAsSingleBySize(int size)
    public PullMessageManage pullMessageSingleBySize(long id1,long id2,int size){
        PullMessageRequestPacket pullMessageRequestPacket=new PullMessageRequestPacket();
        pullMessageRequestPacket.setAsSingleBySize(id1, id2, size);
        writeAndFlushAddListener(pullMessageRequestPacket);
        return this;
    }
    //setAsSingleByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int Size)
    public PullMessageManage pullMessageSingleByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int Size){
        PullMessageRequestPacket pullMessageRequestPacket=new PullMessageRequestPacket();
        pullMessageRequestPacket.setAsSingleByFromMessageIdAndSize(fromMessageId1, Id1, Id2, Bigger, Size);
        writeAndFlushAddListener(pullMessageRequestPacket);
        return this;
    }

}
