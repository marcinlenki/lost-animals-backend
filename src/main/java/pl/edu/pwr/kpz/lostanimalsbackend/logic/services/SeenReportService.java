package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.SeenReportRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.SeenReportDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.SeenReport;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeenReportService {
    private final SeenReportRepository seenReportRepository;
    private final SeenReportDTOMapper seenReportDTOMapper;

    public List<SeenReport> getSeenReportList(){
        return this.seenReportRepository.findAll();
    }

    public SeenReport getSeenReportById(Integer id){
        return this.seenReportRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "seen report with id: " + id + " dose not exists"
                ));
    }

    public void addSeenReport(SeenReport seenReport){
        seenReportRepository.save(seenReport);
    }

    public void deleteSeenReportById(Integer id){
        boolean exists = seenReportRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("seen report with id:" + id + " dose not exists");
        }
        this.seenReportRepository.deleteById(id);
    }

    public void updateSeenReport(Integer id, SeenReport seenReport){
        boolean exists = seenReportRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("seen report with id:" + id + " dose not exists");
        }
        this.seenReportRepository.save(seenReport);
    }

}
