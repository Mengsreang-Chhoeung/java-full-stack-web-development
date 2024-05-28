package com.ms.spring_file_upload.util;

import com.ms.spring_file_upload.exception.BadRequestException;
import com.ms.spring_file_upload.exception.InternalServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public final class FileUtil {
    public static String saveMultipartFile(MultipartFile file, String path) {
        if (file.getSize() <= 0) throw new BadRequestException("No file provided!");

        String filename = UUID.randomUUID().toString();
        String sourceFilename = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() || !Objects.requireNonNull(file.getOriginalFilename()).isBlank() ? file.getOriginalFilename() : file.getName();
        String extension = sourceFilename.contains(".") ? sourceFilename.substring(sourceFilename.lastIndexOf(".")) : "";
        Path pth = !path.isBlank() || !path.isEmpty() ? Paths.get(path) : Paths.get("./");

        try {
            if (Files.notExists(pth))
                Files.createDirectory(pth);

            String fullName = filename + extension;

            file.transferTo(pth.resolve(fullName));

            return fullName;
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
