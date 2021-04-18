package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GetUserInfoRequest
 * @date: 2021/4/15 18:23
 */
public class GetInfoAndFriendRequestPacket extends BasePacket{
    //请求获取用户信息
    //public static final int GET_USER_INFO=1;

    private int type;
    private Long UserId;
    private String obj;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

//    public static int getGetUserInfo() {
//        return GET_USER_INFO;
//    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }
}
