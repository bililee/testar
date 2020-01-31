package com.lee.IMnetty.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * ConsoleCommand
 *
 * @author Lee
 * @date 2020/1/30
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
