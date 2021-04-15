package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GetUserInfoRequest
 * @date: 2021/4/15 18:23
 */
//todo 映射
public class GetInfoRequestPacket extends BasePacket{
    //请求获取用户信息
    //public static final int GET_USER_INFO=1;

    private int type;
    private Long UserId;

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
