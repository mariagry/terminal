package com.example.terminal;

import com.example.terminal.model.Terminal;
import com.example.terminal.patern.command.TerminalInvoker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerminalApplication.class, args);
    }
    @Bean
    public Terminal terminal() {
        return new Terminal();
    }    @Bean
    public TerminalInvoker terminalInvoker() {
        return new TerminalInvoker();
    }
}
