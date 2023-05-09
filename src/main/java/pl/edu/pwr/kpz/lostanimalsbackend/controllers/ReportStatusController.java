package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.ReportStatusService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

import java.util.List;

@RestController
@RequestMapping("report-statuses")
@RequiredArgsConstructor
public class ReportStatusController {

    private final ReportStatusService reportStatusService;

    @GetMapping
    public List<ReportStatus> getReportStatusList(){
        return this.reportStatusService.getReportStatusList();
    }

    @GetMapping(path = "/{id}")
    public ReportStatus getReportStatusById(@PathVariable("id") Integer id){
        return this.reportStatusService.getReportStatusById(id);
    }

    @PostMapping
    public void addReportStatus(@RequestBody ReportStatus reportStatus){
        reportStatusService.addReportStatus(reportStatus);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteReportStatusById(@PathVariable("id") Integer id){
        reportStatusService.deleteReportStatusById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateReportStatus(@PathVariable("id") Integer id, @RequestBody ReportStatus reportStatus){
        reportStatusService.updateReportStatus(id, reportStatus);
    }
}
