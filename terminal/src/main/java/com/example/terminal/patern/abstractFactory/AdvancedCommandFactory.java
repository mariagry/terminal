package com.example.terminal.patern.abstractFactory;


import com.example.terminal.patern.command.TerminalCommand;

public class AdvancedCommandFactory implements TerminalStrategyFactory {
    @Override
    public Command createCommand(String command) {
        if ("advancedCommand1".equalsIgnoreCase(command)) {
            return  new AdvancedCommand1();
        } else if ("advancedCommand2".equalsIgnoreCase(command)) {
            return  new AdvancedCommand2();
        } else {
            return null;
        }
    }


}