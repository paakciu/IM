package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: PullMessageRequestPacket
 * @date: 2021/4/11 15:33
 */
//TODO
public class PullMessageRequestPacket extends BasePacket{
    private boolean isGroup=false;
    private Long fromId;//
    private Long id1;
    private Long id2;
    private Long fromMessageId=-1L;//默认
    private boolean getBigger=true;//根据消息ID往下还是往上数消息数
    private Integer nums=-1;//拉取多少个


    public void openGroup(){
        isGroup=true;
    }
    public void openSingle(){
        isGroup=false;
    }

    public void setAsGroupByFromMessageId(Long fromMessageId1,boolean Bigger){
        openGroup();
        fromMessageId=fromMessageId1;
        getBigger=Bigger;
    }
    public void setAsSingleByFromMessageId(Long Id1,Long Id2,boolean Bigger){
        openSingle();
        id1=Id1;
        id2=Id2;
        getBigger=Bigger;
    }
    public void setAsGroupBySize(int size){
        openGroup();
        nums=size;
    }
    public void setAsSingleBySize(int size){
        openSingle();
        nums=size;
    }

    public void setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size){
        openGroup();
        fromMessageId=fromMessageId1;
        getBigger=Bigger;
        nums=Size;
    }
    public void setAsSingleByFromMessageIdAndSize(Long Id1,Long Id2,boolean Bigger,int Size){
        openSingle();
        id1=Id1;
        id2=Id2;
        getBigger=Bigger;
        nums=Size;
    }



    public boolean isGroup() {
        return isGroup;
    }

    public Long getFromId() {
        return fromId;
    }

    public Long getId1() {
        return id1;
    }

    public Long getId2() {
        return id2;
    }

    public Long getFromMessageId() {
        return fromMessageId;
    }

    public boolean isGetBigger() {
        return getBigger;
    }

    public Integer getNums() {
        return nums;
    }
}
