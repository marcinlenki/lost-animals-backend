package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.ReportStatusRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.ReportStatus;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportStatusService {

    private final ReportStatusRepository reportStatusRepository;

    public List<ReportStatus> getReportStatusList(){
        return this.reportStatusRepository.findAll();
    }

    public ReportStatus getReportStatusById(Integer id){
        return this.reportStatusRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "report status with id: " + id + " dose not exists"
                ));
    }


    public void addReportStatus(ReportStatus reportStatus){
        reportStatusRepository.save(reportStatus);
    }

    public void deleteReportStatusById(Integer id){
        boolean exists = reportStatusRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("report status with id:" + id + " dose not exists");
        }
        reportStatusRepository.deleteById(id);
    }

    //TODO write update
    public void updateReportStatus(Integer id){
        ReportStatus reportStatus = this.reportStatusRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "report status with id: " + id + " dose not exists"
                ));


    }
}
