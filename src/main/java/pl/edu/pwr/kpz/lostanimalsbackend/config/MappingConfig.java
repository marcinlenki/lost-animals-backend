package pl.edu.pwr.kpz.lostanimalsbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class MappingConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
