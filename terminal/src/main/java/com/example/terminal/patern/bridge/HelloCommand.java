package com.example.terminal.patern.bridge;

public class HelloCommand extends Command{
    public HelloCommand(Color color) {
        super(color);
    }

    @Override
    public String commandText() {
        return "Hello " + color.colorText();
    }
}
