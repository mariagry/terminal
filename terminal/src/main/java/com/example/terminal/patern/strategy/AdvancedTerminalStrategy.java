package com.example.terminal.patern.strategy;

public class AdvancedTerminalStrategy implements TerminalStrategy {
    @Override
    public void executeCommand(String command) {
        System.out.println("Виконано складну команду: " + command);
    }
}