package top.paakciu.protocal.packet;

import top.paakciu.protocal.Command;

/**
 * @author paakciu
 * @ClassName: MessagePacket
 * @date: 2021/2/28 0:07
 */
public class MessagePacket extends BasePacket{
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
