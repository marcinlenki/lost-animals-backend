package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.UtilService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UtilController {
    private final UtilService utilService;

    // FOR DEVELOPMENT ONLY !!!
    // Make sure to remove after deploying to production
    @PostMapping("reset")
    public Map<String, String> resetDatabase() {
        return utilService.resetDatabase();
    }

    @GetMapping("healthcheck")
    public Map<String, String> healthcheck() {
        return utilService.getWelcomeMessage();
    }
}
