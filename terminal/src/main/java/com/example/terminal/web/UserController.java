package com.example.terminal.web;

import com.example.terminal.model.TerminalSettings;
import com.example.terminal.model.User;
import com.example.terminal.service.TerminalSettingService;
import com.example.terminal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final TerminalSettingService terminalSettingService;

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        TerminalSettings settings = new TerminalSettings();
        terminalSettingService.save(settings);
        settings.setTextColor("#FFFFFF");
        settings.setWindowColor("#000000");
        user.setTerminalSettings(settings);
         service.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/sign-in");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.findUserById(id);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody User user) {
        user = service.signIn(user);
        return user.getId().toString();
    }

    @GetMapping("/{id}/settings")
    public TerminalSettings getUserSetting(@PathVariable Long id) {
        return service.findSettingUserById(id);
    }
}
