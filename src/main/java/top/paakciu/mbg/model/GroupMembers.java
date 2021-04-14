package top.paakciu.mbg.model;

import java.io.Serializable;

public class GroupMembers implements Serializable {
    private Long id;

    private Long groupId;

    private Long groupUserid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupUserid() {
        return groupUserid;
    }

    public void setGroupUserid(Long groupUserid) {
        this.groupUserid = groupUserid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupUserid=").append(groupUserid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}