package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: OffLineMessageRequest
 * @date: 2021/4/11 18:02
 */
public class OffLineMessageRequestPacket extends BasePacket{
    //Userid
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
