package com.example.terminal.patern.abstractFactory;


public interface TerminalStrategyFactory {
    public abstract Command createCommand(String command);

}
