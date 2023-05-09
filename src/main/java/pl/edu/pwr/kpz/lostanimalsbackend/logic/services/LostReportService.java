package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.LostReportRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LostReportService {
    private final LostReportRepository lostReportRepository;

    public List<LostReport> getLostReportList(){
        return this.lostReportRepository.findAll();
    }

    public LostReport getLostReportById(Integer id){
        return this.lostReportRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "lost report with id: " + id + " dose not exists"
                ));
    }

    public void addLostReport(LostReport lostReport){
        lostReportRepository.save(lostReport);
    }

    public void deleteLostReportById(Integer id){
        boolean exists = lostReportRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
        }
        lostReportRepository.deleteById(id);
    }

    //TODO write update
    public void updateLostReport(Integer id){
        LostReport lostReport = this.lostReportRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "lost report with id: " + id + " dose not exists"
                ));


    }
}
