package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GetUserInfoResposePacket
 * @date: 2021/4/15 18:30
 */
public class GetInfoAndFriendResponsePacket extends BasePacket{
    private String obj;
    private int type;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    //    public Object getObj() {
//        return obj;
//    }
//
//    public void setObj(Object obj) {
//        this.obj = obj;
//    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
