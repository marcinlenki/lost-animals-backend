package pl.edu.pwr.kpz.lostanimalsbackend.model.dto;

import pl.edu.pwr.kpz.lostanimalsbackend.model.entities.AnimalPicture;

import java.util.List;

public record MultipleImageUploadDTO(List<AnimalPicture> results, List<Exception> failed) {
}
