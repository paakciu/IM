package top.paakciu;

import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.service.NormalMessageService;

import java.util.Date;

/**
 * @author paakciu
 * @ClassName: test06
 * @date: 2021/3/31 15:38
 */
public class test06 {
    public static void main(String[] args) {
        NormalMsg normalMsg=new NormalMsg();
        normalMsg.setNmMsg("nihao");
        normalMsg.setNmTime(new Date());
        normalMsg.setNmToid(21l);
        normalMsg.setNmFromid(25L);
        System.out.println(NormalMessageService.addNomalMessage(normalMsg));
    }
}
