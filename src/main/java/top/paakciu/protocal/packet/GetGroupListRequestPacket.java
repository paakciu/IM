package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GetGroupListRequestPacket
 * @date: 2021/4/15 12:22
 */
public class GetGroupListRequestPacket extends BasePacket{
    private Long userId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
