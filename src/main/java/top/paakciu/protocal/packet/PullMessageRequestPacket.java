package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: PullMessageRequestPacket
 * @date: 2021/4/11 15:33
 */
//TODO
public class PullMessageRequestPacket extends BasePacket{
    private Long groupid;//群聊消息号
    private Long id1;
    private Long id2;
    private Long fromMessageId;//默认
    private boolean isGroup;
    private boolean getBigger;//根据消息ID往下还是往上数消息数
    private Integer nums;//拉取多少个
    private Integer mods;//只有三档，如下
    /*
    //1---这是根据消息的id，向上或者向下获取所有消息
    //public void setAsGroupByFromMessageId(Long fromMessageId1,boolean Bigger)
    //2---这是根据size 从最新的消息获取特定数量
    //public void setAsGroupBySize(int size)
    //3---这是根据消息的id，向上或者向下获取 特定条数的消息
    //public void setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size)
    */

    public void openGroup(){
        this.isGroup=true;
    }
    public void openSingle(){
        this.isGroup=false;
    }

    public void setAsGroupByFromMessageId(Long fromMessageId1,boolean Bigger){
        openGroup();
        fromMessageId=fromMessageId1;
        getBigger=Bigger;
        mods=1;
    }
    public void setAsSingleByFromMessageId(long fromMessageId,long Id1,long Id2,boolean Bigger){
        openSingle();
        this.fromMessageId=fromMessageId;
        this.id1=Id1;
        this.id2=Id2;
        this.getBigger=Bigger;
        this.mods=1;
    }
    public void setAsGroupBySize(long groupid,int size){
        openGroup();
        this.
        nums=size;
        mods=2;
    }
    public void setAsSingleBySize(long Id1,long Id2,int size){
        openSingle();
        this.id1=Id1;
        this.id2=Id2;
        nums=size;
        mods=2;
    }

    public void setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size){
        openGroup();
        fromMessageId=fromMessageId1;
        getBigger=Bigger;
        nums=Size;
        mods=3;
    }
    public void setAsSingleByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int Size){
        openSingle();
        this.fromMessageId=fromMessageId1;
        id1=Id1;
        id2=Id2;
        getBigger=Bigger;
        nums=Size;
        mods=3;
    }


    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    public Long getFromMessageId() {
        return fromMessageId;
    }

    public void setFromMessageId(Long fromMessageId) {
        this.fromMessageId = fromMessageId;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public boolean isGetBigger() {
        return getBigger;
    }

    public void setGetBigger(boolean getBigger) {
        this.getBigger = getBigger;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getMods() {
        return mods;
    }

    public void setMods(Integer mods) {
        this.mods = mods;
    }

    @Override
    public String toString() {
        return "PullMessageRequestPacket{" +
                "groupid=" + groupid +
                ", id1=" + id1 +
                ", id2=" + id2 +
                ", fromMessageId=" + fromMessageId +
                ", isGroup=" + isGroup +
                ", getBigger=" + getBigger +
                ", nums=" + nums +
                ", mods=" + mods +
                '}';
    }
}
