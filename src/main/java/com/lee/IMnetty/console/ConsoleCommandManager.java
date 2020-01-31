package com.lee.IMnetty.console;

import io.netty.channel.Channel;
import jline.ConsoleOperations;

import java.util.HashMap;
import java.util.Scanner;

/**
 * ConsoleCommandManager
 *
 * @author Lee
 * @date 2020/1/30
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private HashMap<String, ConsoleCommand> consoleCommandHashMap;

    public ConsoleCommandManager() {
        consoleCommandHashMap = new HashMap<>();
        // 这里放各个命令对应的相应的东西
//        consoleCommandHashMap.put("sendToUser");
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = consoleCommandHashMap.get(command);
        if (consoleCommand == null) {
            System.out.println("该方法不存在");
            return ;
        }
        consoleCommand.exec(scanner, channel);

    }
}
