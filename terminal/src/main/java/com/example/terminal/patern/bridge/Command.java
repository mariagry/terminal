package com.example.terminal.patern.bridge;

public abstract class Command {
    protected Color color;

    public Command(Color color) {
        this.color = color;
    }
    public abstract String commandText();

}
