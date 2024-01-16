package com.example.terminal.web;

import com.example.terminal.model.Terminal;
import com.example.terminal.model.TerminalSettings;
import com.example.terminal.patern.abstractFactory.AdvancedCommandFactory;
import com.example.terminal.patern.abstractFactory.Command;
import com.example.terminal.patern.abstractFactory.SimpleCommandFactory;
import com.example.terminal.patern.abstractFactory.TerminalStrategyFactory;
import com.example.terminal.patern.bridge.HelloCommand;
import com.example.terminal.patern.bridge.YellowColor;
import com.example.terminal.patern.command.ChangeColorCommand;
import com.example.terminal.patern.command.ResizeWindowCommand;
import com.example.terminal.patern.command.TerminalCommand;
import com.example.terminal.patern.command.TerminalInvoker;
import com.example.terminal.patern.interpreter.BinaryOperationExpression;
import com.example.terminal.patern.interpreter.Expression;
import com.example.terminal.patern.interpreter.NumberExpression;
import com.example.terminal.patern.strategy.AdvancedTerminalStrategy;
import com.example.terminal.patern.strategy.SimpleTerminalStrategy;
import com.example.terminal.service.TerminalSettingService;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terminal")
@AllArgsConstructor
public class TerminalController {
    private final TerminalSettingService service;
    private final Terminal terminal;
    private final TerminalInvoker invoker;

    @GetMapping("/{id}")
    public TerminalSettings getTerminalSettingsById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/user/{id}")
    public TerminalSettings getTerminalSettingsByUserId(@PathVariable Long id) {
        return service.findByUserId(id);
    }

    @PutMapping("/{id}/settings")
    public TerminalSettings changeSetting(@PathVariable Long id, @RequestBody TerminalSettings terminalSettings) {
        return service.changeSettings(id, terminalSettings);
    }

    @PutMapping("/user/{id}/settings")
    public TerminalSettings changeSettingByUserId(@PathVariable Long id, @RequestBody TerminalSettings terminalSettings) {
        return service.changeSettingsByUserId(id, terminalSettings);
    }

    @GetMapping("/execute/{command}")
    public String executeCommand(@PathVariable String command) {
        terminal.executeCommand(command);
        return "Command executed: " + command;
    }

    @GetMapping("/changeStrategy/{strategy}")
    public String changeStrategy(@PathVariable String strategy) {
        if ("simple".equalsIgnoreCase(strategy)) {
            terminal.setTerminalStrategy(new SimpleTerminalStrategy());
            return "Strategy changed to SimpleTerminalStrategy";
        } else if ("advanced".equalsIgnoreCase(strategy)) {
            terminal.setTerminalStrategy(new AdvancedTerminalStrategy());
            return "Strategy changed to AdvancedTerminalStrategy";
        } else {
            return "Unknown strategy";
        }
    }


    @PostMapping("/changeColor/{color}")
    public String changeColor(@PathVariable String color) {
        TerminalCommand command = new ChangeColorCommand(color);
        invoker.setCommand(command);
        invoker.executeCommand();
        return "Color command executed";
    }

    @PostMapping("/resizeWindow/{width}/{height}")
    public String resizeWindow(@PathVariable int width, @PathVariable int height) {
        TerminalCommand command = new ResizeWindowCommand(width, height);
        invoker.setCommand(command);
        invoker.executeCommand();
        return "Resize command executed";
    }

    @GetMapping("/command")
    public String execute(@RequestParam String command) {
        TerminalStrategyFactory factory;

        if ("advancedCommand1".equals(command) || "advancedCommand2".equals(command)) {
            factory = new AdvancedCommandFactory();
        } else if ("simpleCommand1".equals(command) || "simpleCommand2".equals(command)) {
            factory = new SimpleCommandFactory();
        } else {
            PowerShellResponse response = PowerShell.executeSingleCommand(command);
            return response.getCommandOutput();
        }

        return factory.createCommand(command).execute();
    }

    @GetMapping("/b")
    public String bridgeCommand(@RequestParam String command) {
        if (command.startsWith("y") && command.endsWith("h")) {
            HelloCommand command1 = new HelloCommand(new YellowColor());
            return command1.commandText();
        }
        return "Unknown command";
    }


    @GetMapping("/c")
    public int calculateExpression(@RequestParam String command) {
        try {
            String[] elements = command.split("\\s+");
            Expression result = buildExpression(elements);
            return result.interpret();
        } catch (Exception e) {
            return 0;
        }
    }

    private Expression buildExpression(String[] elements) {
        if (elements.length % 2 == 0) {
            throw new IllegalArgumentException("Неправильний формат виразу");
        }

        return buildExpressionRecursively(elements, 0);
    }

    private Expression buildExpressionRecursively(String[] elements, int index) {
        Expression left = new NumberExpression(Integer.parseInt(elements[index]));

        if (index + 1 < elements.length) {
            char operation = elements[index + 1].charAt(0);
            Expression right = buildExpressionRecursively(elements, index + 2);
            return new BinaryOperationExpression(left, right, operation);
        }

        return left;
    }


}
