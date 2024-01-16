package com.example.terminal.service;

import com.example.terminal.model.TerminalSettings;

public interface TerminalSettingService {
    TerminalSettings findById(Long id);

    TerminalSettings changeSettings(Long id, TerminalSettings terminalSettings);

    void save(TerminalSettings settings);

    TerminalSettings findByUserId(Long id);

    TerminalSettings changeSettingsByUserId(Long id, TerminalSettings terminalSettings);
}
