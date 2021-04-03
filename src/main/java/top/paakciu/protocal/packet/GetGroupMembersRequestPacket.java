package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GetGroupMembersRequestPacket
 * @date: 2021/4/3 15:56
 */
public class GetGroupMembersRequestPacket extends BasePacket{
    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
