package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: JoinGroupRequestPacket
 * @date: 2021/4/3 14:32
 */
public class JoinGroupRequestPacket extends BasePacket{

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
