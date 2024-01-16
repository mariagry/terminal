package com.example.terminal.patern.abstractFactory;

public class SimpleCommand1 implements Command {
    @Override
    public String execute() {
        return  "Executing Simple Command 1";
    }
}