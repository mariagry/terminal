package com.example.terminal.service.impl;

import com.example.terminal.model.TerminalSettings;
import com.example.terminal.model.User;
import com.example.terminal.repository.TerminalSettingsRepository;
import com.example.terminal.repository.UserRepository;
import com.example.terminal.service.TerminalSettingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TerminalSettingServiceImpl implements TerminalSettingService {
    private final TerminalSettingsRepository terminalSettingRepository;
    private final UserRepository userRepository;

    @Override
    public TerminalSettings findById(Long id) {
        return terminalSettingRepository.findById(id).orElse(null);
    }

    @Override
    public TerminalSettings changeSettings(Long id, TerminalSettings terminalSettings) {
        return terminalSettingRepository.findById(id)
                .map(settings -> {
                    settings.setWindowColor(terminalSettings.getWindowColor());
                    settings.setTextColor(terminalSettings.getTextColor());
                    return terminalSettingRepository.save(settings);
                })
                .orElseThrow(() -> new EntityNotFoundException("TerminalSettings with id " + id + " not found"));

    }

    @Override
    public void save(TerminalSettings settings) {
        terminalSettingRepository.save(settings);
    }

    @Override
    public TerminalSettings findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
         return user.getTerminalSettings();
        }
        return null;
    }

    @Override
    public TerminalSettings changeSettingsByUserId(Long id, TerminalSettings terminalSettings) {
        TerminalSettings current = userRepository.findById(id).get().getTerminalSettings();
        current.setTextColor(terminalSettings.getTextColor());
        current.setWindowColor(terminalSettings.getWindowColor());
        save(current);
        return  current;
    }
}
