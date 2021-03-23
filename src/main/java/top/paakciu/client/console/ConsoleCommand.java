package top.paakciu.client.console;

import java.nio.channels.Channel;

public interface ConsoleCommand {
    void exec(String command, Channel channel);
}