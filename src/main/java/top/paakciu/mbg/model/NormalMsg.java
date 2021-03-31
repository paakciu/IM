package top.paakciu.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class NormalMsg implements Serializable {
    private Long id;

    private Long nmFromid;

    private Long nmToid;

    private Date nmTime;

    private Byte nmState;

    private Byte nmType;

    private String nmMsg;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNmFromid() {
        return nmFromid;
    }

    public void setNmFromid(Long nmFromid) {
        this.nmFromid = nmFromid;
    }

    public Long getNmToid() {
        return nmToid;
    }

    public void setNmToid(Long nmToid) {
        this.nmToid = nmToid;
    }

    public Date getNmTime() {
        return nmTime;
    }

    public void setNmTime(Date nmTime) {
        this.nmTime = nmTime;
    }

    public Byte getNmState() {
        return nmState;
    }

    public void setNmState(Byte nmState) {
        this.nmState = nmState;
    }

    public Byte getNmType() {
        return nmType;
    }

    public void setNmType(Byte nmType) {
        this.nmType = nmType;
    }

    public String getNmMsg() {
        return nmMsg;
    }

    public void setNmMsg(String nmMsg) {
        this.nmMsg = nmMsg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nmFromid=").append(nmFromid);
        sb.append(", nmToid=").append(nmToid);
        sb.append(", nmTime=").append(nmTime);
        sb.append(", nmState=").append(nmState);
        sb.append(", nmType=").append(nmType);
        sb.append(", nmMsg=").append(nmMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}