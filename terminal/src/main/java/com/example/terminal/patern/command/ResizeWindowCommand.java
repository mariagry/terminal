package com.example.terminal.patern.command;

public class ResizeWindowCommand implements TerminalCommand {
    private int width;
    private int height;

    public ResizeWindowCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute() {
        System.out.println("Resizing window to width: " + width + ", height: " + height);

    }
}