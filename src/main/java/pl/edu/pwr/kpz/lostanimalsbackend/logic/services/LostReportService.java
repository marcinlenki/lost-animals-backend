////package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;
////
////import jakarta.transaction.Transactional;
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Service;
////import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.LostReportRepository;
////import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.LostReportDTOMapper;
////import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;
////
////import java.util.List;
////
////@Service
////@Transactional
////@RequiredArgsConstructor
////public class LostReportService {
////    private final LostReportRepository lostReportRepository;
////    private final LostReportDTOMapper lostReportDTOMapper;
////
////    public List<LostReport> getLostReportList(){
////        return this.lostReportRepository.findAll();
////    }
////
////    public LostReport getLostReportById(Integer id){
////        return this.lostReportRepository.findById(id)
////                .orElseThrow(()-> new IllegalStateException(
////                        "lost report with id: " + id + " dose not exists"
////                ));
////    }
////
////    public void addLostReport(LostReport lostReport){
////        lostReportRepository.save(lostReport);
////    }
////
////    public void deleteLostReportById(Integer id){
////        boolean exists = lostReportRepository.existsById(id);
////        if(!exists){
////            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
////        }
////        this.lostReportRepository.deleteById(id);
////    }
////
////    public void updateLostReport(Integer id, LostReport lostReport){
////        boolean exists = lostReportRepository.existsById(id);
////        if(!exists){
////            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
////        }
////        this.lostReportRepository.save(lostReport);
////    }
////}
//
//
//
//package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.LostReportRepository;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.LostReportDTOMapper;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class LostReportService {
//    private final LostReportRepository lostReportRepository;
//    private final LostReportDTOMapper lostReportDTOMapper;
//
//    public List<LostReport> getLostReportList(){
//        return this.lostReportRepository.findAll();
//    }
//
//    public LostReport getLostReportById(Integer id){
//        return this.lostReportRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException(
//                        "lost report with id: " + id + " dose not exists"
//                ));
//    }
//
//    public void addLostReport(LostReport lostReport){
//        lostReportRepository.save(lostReport);
//    }
//
//    public void deleteLostReportById(Integer id){
//        boolean exists = lostReportRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
//        }
//        this.lostReportRepository.deleteById(id);
//    }
//
//    public void updateLostReport(Integer id, LostReport lostReport){
//        boolean exists = lostReportRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
//        }
//        this.lostReportRepository.save(lostReport);
//    }
//}


//package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.LostReportRepository;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.LostReportDTOMapper;
//import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class LostReportService {
//    private final LostReportRepository lostReportRepository;
//    private final LostReportDTOMapper lostReportDTOMapper;
//
//    public List<LostReport> getLostReportList(){
//        return this.lostReportRepository.findAll();
//    }
//
//    public LostReport getLostReportById(Integer id){
//        return this.lostReportRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException(
//                        "lost report with id: " + id + " dose not exists"
//                ));
//    }
//
//    public void addLostReport(LostReport lostReport){
//        lostReportRepository.save(lostReport);
//    }
//
//    public void deleteLostReportById(Integer id){
//        boolean exists = lostReportRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
//        }
//        this.lostReportRepository.deleteById(id);
//    }
//
//    public void updateLostReport(Integer id, LostReport lostReport){
//        boolean exists = lostReportRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("lost report with id:" + id + " dose not exists");
//        }
//        this.lostReportRepository.save(lostReport);
//    }
//}



package pl.edu.pwr.kpz.lostanimalsbackend.logic.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pwr.kpz.lostanimalsbackend.logic.repositories.BaseRepository;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportRequestDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.LostReportResponseDTO;
import pl.edu.pwr.kpz.lostanimalsbackend.model.dto.mapper.LostReportDTOMapper;
import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.LostReport;

@Service
public class LostReportService extends MappedCrudService<LostReport, LostReportRequestDTO, LostReportResponseDTO> {
    public LostReportService(BaseRepository<LostReport> repository, LostReportDTOMapper mapper) {
        super(repository, LoggerFactory.getLogger(LostReportService.class), mapper);
    }
}
