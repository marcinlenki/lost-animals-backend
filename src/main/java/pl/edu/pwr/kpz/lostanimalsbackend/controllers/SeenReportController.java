package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.SeenReportService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportRequestInfo;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.SeenReportResponseDTO;

import java.util.List;

@RestController
@RequestMapping("seen")
@RequiredArgsConstructor
public class SeenReportController {
    private final SeenReportService seenReportService;

    @GetMapping
    public List<SeenReportResponseDTO> getSeenReportList() {
        return seenReportService.list();
    }

    @GetMapping("{id}")
    public SeenReportResponseDTO getSeenReportById(@PathVariable("id") int id) {
        return seenReportService.getOne(id);
    }

    @PostMapping
    public SeenReportResponseDTO addSeenReport(@Validated(SeenReportRequestInfo.class) @RequestBody SeenReportRequestDTO seenReport) {
        return seenReportService.add(seenReport);
    }

    @PutMapping("{id}")
    public void updateSeenReport(@Validated(SeenReportRequestInfo.class) @PathVariable("id") int id, @RequestBody SeenReportRequestDTO seenReport) {
        seenReportService.update(id, seenReport);
    }

    @DeleteMapping("{id}")
    public void deleteSeenReportById(@PathVariable("id") int id){
        seenReportService.delete(id);
    }
}
