package top.paakciu.mbg.model;

import java.io.Serializable;

public class NormalMsgOffline implements Serializable {
    private Long nmoId;

    private Long msgid;

    private Long nmoToid;

    private Byte nmoState;

    private static final long serialVersionUID = 1L;

    public Long getNmoId() {
        return nmoId;
    }

    public void setNmoId(Long nmoId) {
        this.nmoId = nmoId;
    }

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public Long getNmoToid() {
        return nmoToid;
    }

    public void setNmoToid(Long nmoToid) {
        this.nmoToid = nmoToid;
    }

    public Byte getNmoState() {
        return nmoState;
    }

    public void setNmoState(Byte nmoState) {
        this.nmoState = nmoState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nmoId=").append(nmoId);
        sb.append(", msgid=").append(msgid);
        sb.append(", nmoToid=").append(nmoToid);
        sb.append(", nmoState=").append(nmoState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}