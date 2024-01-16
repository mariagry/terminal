package com.example.terminal.patern.abstractFactory;



public class SimpleCommandFactory implements TerminalStrategyFactory {
    @Override
    public Command createCommand(String command) {
        if ("simpleCommand1".equalsIgnoreCase(command)) {
            return new SimpleCommand1();
        } else if ("simpleCommand2".equalsIgnoreCase(command)) {
            return new SimpleCommand2();
        } else {
            return null;
        }
    }
}