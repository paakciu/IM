package top.paakciu.mbg.model;

import java.io.Serializable;

public class Friend implements Serializable {
    private Long id;

    private Long userid;

    private Long frienduserid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFrienduserid() {
        return frienduserid;
    }

    public void setFrienduserid(Long frienduserid) {
        this.frienduserid = frienduserid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", frienduserid=").append(frienduserid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}