package pl.edu.pwr.kpz.lostanimalsbackend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class FileUtils {
    private FileUtils() {}

    public static File convertMultipartToFile(MultipartFile multipartFile, String resultFilepath) throws Exception {
        if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null)
            throw new Exception("Invalid multipartFile properties!");

        else if(resultFilepath == null || resultFilepath.isEmpty())
            throw new Exception("Invalid resultFilepath! [" + resultFilepath + "]");

        File file = new File(resultFilepath);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());

        } catch (IOException e) {
            throw new Exception("Unable to locally save file " + multipartFile.getOriginalFilename(), e);
        }

        return file;
    }

    public static File convertMultipartToFile(MultipartFile multipartFile, Path resultFilepath) throws Exception {
        if (resultFilepath == null)
            throw new Exception("Invalid path argument!");

        return convertMultipartToFile(multipartFile, resultFilepath.toAbsolutePath().toString());
    }


    public static String generateStorageFilename(MultipartFile multipartFile) {
        return LocalDateTime.now() + "_" + multipartFile.getOriginalFilename();
    }
}
