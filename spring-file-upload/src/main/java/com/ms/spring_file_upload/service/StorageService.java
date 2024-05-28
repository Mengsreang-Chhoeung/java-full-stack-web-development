package com.ms.spring_file_upload.service;

import com.ms.spring_file_upload.exception.InternalServerErrorException;
import com.ms.spring_file_upload.exception.NotFoundException;
import com.ms.spring_file_upload.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StorageService {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/files";

    public String upload(MultipartFile file) {
        return FileUtil.saveMultipartFile(file, FILE_PATH);
    }

    public void deleteFile(String fileName) {
        Path p = Paths.get(FILE_PATH);
        Path f = p.resolve(fileName);

        try {
            if (Files.exists(f)) {
                Files.delete(f);
            }
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    public void loadFile(String filename, HttpServletResponse response) {
        try {
            Path p = Paths.get(FILE_PATH).resolve(filename);
            Resource resource = new UrlResource(p.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new NotFoundException("File not found!");
            }

            response.setHeader(HttpHeaders.CONTENT_TYPE, Files.probeContentType(p));
            response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + Files.size(p));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + "\"" + filename + "\"");

            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());

        } catch (Exception ex) {
            if (ex instanceof NotFoundException) throw new NotFoundException(ex.getMessage());

            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
