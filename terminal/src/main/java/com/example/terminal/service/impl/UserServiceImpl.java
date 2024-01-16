package com.example.terminal.service.impl;

import com.example.terminal.model.TerminalSettings;
import com.example.terminal.model.User;
import com.example.terminal.repository.UserRepository;
import com.example.terminal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TerminalSettings findSettingUserById(Long id) {
        return findUserById(id).getTerminalSettings();
    }

    @Override
    public User signIn(User currentUser) {
        User user = repository.findByUsername(currentUser.getUsername());
        if (user.getPassword().equals(currentUser.getPassword())){
            return user;
        }
        return null;
    }
}
