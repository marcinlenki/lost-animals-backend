package pl.edu.pwr.kpz.lostanimalsbackend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class FileStorageConfig {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageConfig.class);

    @Value("${access.key.id}")
    private String accessKeyId;

    @Value("${access.key.secret}")
    private String accessKeySecret;

    @Value("${s3.region.name}")
    private String s3RegionName;

    @Value("${images.directory}")
    private String imagesDirPath;

    public String getImagesDirPath() {
        return imagesDirPath;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeImageDirectory() {
        try {
            Files.createDirectories(Path.of(imagesDirPath));

        } catch (IOException e) {
            String errorMessage = "Unable to initialize images directory, " +
                    "the images will be saved in the current working directory.";

            logger.warn(errorMessage, e);
            imagesDirPath = "";
        }
    }

    @Bean
    public AmazonS3 s3Client() {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(s3RegionName)
                .build();
    }
}
