package com.example.terminal.patern.command;

public class ChangeColorCommand implements TerminalCommand {
    private final String color;

    public ChangeColorCommand(String color) {
        this.color = color;
    }

    @Override
    public void execute() {
        System.out.println("Changing color to: " + color);
    }
}