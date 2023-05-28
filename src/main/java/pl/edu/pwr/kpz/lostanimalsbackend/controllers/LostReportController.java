package pl.edu.pwr.kpz.lostanimalsbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.services.LostReportService;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportResponseDTO;

@RestController
@RequestMapping("lost")
@RequiredArgsConstructor
public class LostReportController {

    private final LostReportService lostReportService;

    @GetMapping
    public Page<LostReportResponseDTO> getLostReportList(Pageable pageable){
        return lostReportService.list(pageable);
    }

    @GetMapping("{id}")
    public LostReportResponseDTO getLostReportById(@PathVariable("id") int id) {
        return lostReportService.getOne(id);
    }

    @PostMapping
    public LostReportResponseDTO addLostReport(@Valid @RequestBody LostReportRequestDTO lostReport) {
        return lostReportService.add(lostReport);
    }

    @PutMapping("{id}")
    public LostReportResponseDTO updateLostReport(@Valid @PathVariable("id") int id, @RequestBody LostReportRequestDTO lostReport) {
        return lostReportService.update(id, lostReport);
    }

    @DeleteMapping("{id}")
    public void deleteLostReportById(@PathVariable("id") int id) {
        lostReportService.delete(id);
    }
}
