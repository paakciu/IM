package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: OffLineMessageRequest
 * @date: 2021/4/11 18:02
 */
public class OffLineMessageRequestPacket extends BasePacket{
    //Userid
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
