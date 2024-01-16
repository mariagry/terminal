package com.example.terminal.repository;

import com.example.terminal.model.TerminalSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalSettingsRepository extends JpaRepository<TerminalSettings,Long> {
}
