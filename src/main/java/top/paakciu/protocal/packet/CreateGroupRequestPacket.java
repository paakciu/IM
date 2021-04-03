package top.paakciu.protocal.packet;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: CreateGroupRequestPacket
 * @date: 2021/4/2 13:50
 */
public class CreateGroupRequestPacket extends BasePacket{
    //拉取进入群聊的用户列表
    private List<Long> userIdList;

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }
}
