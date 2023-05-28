package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.ReportStatusService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

@RestController
@RequestMapping("report-statuses")
@RequiredArgsConstructor
public class ReportStatusController {

    private final ReportStatusService reportStatusService;

    @GetMapping
    public Page<ReportStatus> getReportStatusList(Pageable pageable) {
        return this.reportStatusService.list(pageable);
    }

    @GetMapping("{id}")
    public ReportStatus getReportStatusById(@PathVariable("id") int id) {
        return this.reportStatusService.getOne(id);
    }

    @PostMapping
    public void addReportStatus(@RequestBody ReportStatus reportStatus) {
        reportStatusService.add(reportStatus);
    }

    @PutMapping( "{id}")
    public void updateReportStatus(@PathVariable("id") int id, @RequestBody ReportStatus reportStatus) {
        reportStatusService.update(id, reportStatus);
    }

    @DeleteMapping( "{id}")
    public void deleteReportStatusById(@PathVariable("id") int id) {
        reportStatusService.delete(id);
    }
}
