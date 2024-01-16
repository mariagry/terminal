package com.example.terminal.patern.bridge;

import lombok.Data;

@Data
public abstract class Color {
    protected String color;
    public abstract String colorText();
}
