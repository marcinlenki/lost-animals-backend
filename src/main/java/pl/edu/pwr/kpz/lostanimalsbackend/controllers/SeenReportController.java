package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.SeenReportService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.SeenReport;

import java.util.List;

@RestController
@RequestMapping("seen-reports")
@RequiredArgsConstructor
public class SeenReportController {
    private final SeenReportService seenReportService;

    @GetMapping
    public List<SeenReport> getSeenReportList(){
        return this.seenReportService.getSeenReportList();
    }

    @GetMapping(path = "/{id}")
    public SeenReport getSeenReportById(@PathVariable("id") Integer id){
        return this.seenReportService.getSeenReportById(id);
    }

    @PostMapping
    public void addSeenReport(@RequestBody SeenReport seenReport){
        seenReportService.addSeenReport(seenReport);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSeenReportById(@PathVariable("id") Integer id){
        seenReportService.deleteSeenReportById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateSeenReport(@PathVariable("id") Integer id, @RequestBody SeenReport seenReport){
        seenReportService.updateSeenReport(id, seenReport);
    }
}
