package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: QuitGroupRequestPacket
 * @date: 2021/4/3 15:26
 */
public class QuitGroupRequestPacket extends BasePacket{
    private Long groupId;
    private Long userId;
    //TODO 可以添加是否被踢，踢人者是谁

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
