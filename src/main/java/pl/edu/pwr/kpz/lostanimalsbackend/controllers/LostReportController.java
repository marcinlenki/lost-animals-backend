package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.LostReportService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

import java.util.List;

@RestController
@RequestMapping("lost-reports")
@RequiredArgsConstructor
public class LostReportController {

    private final LostReportService lostReportService;

    @GetMapping
    public List<LostReport> getLostReportList(){
        return this.lostReportService.getLostReportList();
    }

    @GetMapping(path = "/{id}")
    public LostReport getLostReportById(@PathVariable("id") Integer id){
        return this.lostReportService.getLostReportById(id);
    }

    @PostMapping
    public void addLostReport(@RequestBody LostReport lostReport){
        lostReportService.addLostReport(lostReport);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteLostReportById(@PathVariable("id") Integer id){
        lostReportService.deleteLostReportById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateLostReport(@PathVariable("id") Integer id, @RequestBody LostReport lostReport){
        lostReportService.updateLostReport(id, lostReport);
    }
}
