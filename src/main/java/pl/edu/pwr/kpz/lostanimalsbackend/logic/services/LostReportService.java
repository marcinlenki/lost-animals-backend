package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.LostReportRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LostReportService implements ConvertRequestDTO<LostReport, LostReportResponseDTO> {
    private final LostReportRepository lostReportRepository;
    private final ModelMapper modelMapper;

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
        this.lostReportRepository.deleteById(id);
    }

    public void updateLostReport(Integer id, LostReport lostReport){
        boolean exists = lostReportRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
        }
        this.lostReportRepository.save(lostReport);
    }

    @Override
    public LostReport convertDtoToEmptyEntity(LostReportResponseDTO dto) {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper.map(dto, LostReport.class);
    }

    @Override
    public LostReport convertDtoToFullEntity(LostReportResponseDTO dto) {
        return null;
    }
}
