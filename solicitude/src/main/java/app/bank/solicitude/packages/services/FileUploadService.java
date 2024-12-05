package app.bank.solicitude.packages.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {
    private final Path fileStorageLocation = Paths.get(Paths.get("").toAbsolutePath().toString() + "/uploads/");

    public byte[] uploadFile(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    public String getContentType(String fileName) {
        try {
            return Files.probeContentType(Paths.get(fileName));
        } catch (IOException e) {
            return "application/octet-stream";
        }
    }
}
