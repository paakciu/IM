package top.paakciu.mbg.model;

import java.io.Serializable;

public class GroupMsgOffline implements Serializable {
    private Long id;

    private Long groupid;

    private Long toid;

    private Integer type;

    private Long firstgroupmsgid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getToid() {
        return toid;
    }

    public void setToid(Long toid) {
        this.toid = toid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getFirstgroupmsgid() {
        return firstgroupmsgid;
    }

    public void setFirstgroupmsgid(Long firstgroupmsgid) {
        this.firstgroupmsgid = firstgroupmsgid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupid=").append(groupid);
        sb.append(", toid=").append(toid);
        sb.append(", type=").append(type);
        sb.append(", firstgroupmsgid=").append(firstgroupmsgid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}