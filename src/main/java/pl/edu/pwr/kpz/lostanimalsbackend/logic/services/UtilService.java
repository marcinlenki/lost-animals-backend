package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.UtilityRepository;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilService {
    private final UtilityRepository utilityRepository;

    public Map<String, String> resetDatabase() {
        utilityRepository.resetDatabaseContents();
        return Map.of("message", "Database reset successful!");
    }

    public Map<String, String> getWelcomeMessage() {
        return Map.of("message", "Welcome! It's " + new Date() + " and server is up and running.");
    }
}
