package com.example.terminal.model;

import com.example.terminal.patern.strategy.TerminalStrategy;


public class Terminal {
    private TerminalStrategy terminalStrategy;

    public Terminal() {

    }

    public Terminal(TerminalStrategy terminalStrategy) {
        this.terminalStrategy = terminalStrategy;
    }

    public void executeCommand(String command) {
        terminalStrategy.executeCommand(command);
    }

    public void setTerminalStrategy(TerminalStrategy terminalStrategy) {
        this.terminalStrategy = terminalStrategy;
    }
}