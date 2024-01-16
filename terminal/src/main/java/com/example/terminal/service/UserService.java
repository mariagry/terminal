package com.example.terminal.service;

import com.example.terminal.model.TerminalSettings;
import com.example.terminal.model.User;

public interface UserService {
    User saveUser(User user);

    User findUserById(Long id);

    TerminalSettings findSettingUserById(Long id);

    User signIn(User user);
}
