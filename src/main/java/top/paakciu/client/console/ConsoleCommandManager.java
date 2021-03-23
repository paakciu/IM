package top.paakciu.client.console;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paakciu
 * @ClassName: ConsoleCommandManager
 * @date: 2021/3/7 17:43
 */
public class ConsoleCommandManager {
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
//        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
//        consoleCommandMap.put("logout", new LogoutConsoleCommand());
//        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

//    @Override
//    public void exec(Scanner scanner, Channel channel) {
//        //  获取第一个指令
//        String command = scanner.next();
//
//        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
//
//        if (consoleCommand != null) {
//            consoleCommand.exec(scanner, channel);
//        } else {
//            System.err.println("无法识别[" + command + "]指令，请重新输入!");
//        }
//    }
}
