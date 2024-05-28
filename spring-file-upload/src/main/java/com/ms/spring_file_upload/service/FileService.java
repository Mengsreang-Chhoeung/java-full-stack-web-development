package com.ms.spring_file_upload.service;

import com.ms.spring_file_upload.exception.BadRequestException;
import com.ms.spring_file_upload.exception.InternalServerErrorException;
import com.ms.spring_file_upload.exception.NotFoundException;
import com.ms.spring_file_upload.model.entity.FileEntity;
import com.ms.spring_file_upload.model.request.file.UpdateFileNameRequest;
import com.ms.spring_file_upload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FileService {
    private final FileRepository fileRepository;
    private final StorageService storageService;

    @Autowired
    public FileService(FileRepository fileRepository, StorageService storageService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @Transactional
    public FileEntity upload(MultipartFile file) {
       String fileName =  this.storageService.upload(file);

        FileEntity f = new FileEntity();
        f.setName(fileName);
        f.setOriginalName(file.getOriginalFilename());
        f.setSize(file.getSize());
        f.setType(file.getContentType());

        try {
            return this.fileRepository.save(f);
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    public List<FileEntity> batchUpload(List<MultipartFile> files) {
        List<FileEntity> fileEntities = new ArrayList<>();

        for (MultipartFile file : files) {
            fileEntities.add(this.upload(file));
        }

        return fileEntities;
    }

    public Page<FileEntity> findAll(int page, int pageSize, boolean isTrash, String q) {
        if (isTrash) {
            return this.fileRepository.findAllByOriginalNameContainsAndDeletedAtIsNotNull(q, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt")));
        } else {
            System.out.println("Hei q: " + q);
            return this.fileRepository.findAllByOriginalNameContainsAndDeletedAtIsNull(q, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt")));
        }
    }

    @Transactional
    public FileEntity restore(Long id) {
        FileEntity data = this.fileRepository.findByIdAndDeletedAtIsNotNull(id).orElseThrow(() -> new NotFoundException("Not Found!"));

        data.setDeletedAt(null);

        try {
            return this.fileRepository.save(data);
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    public FileEntity updateFileName(Long id, UpdateFileNameRequest request) {
        if (request.getName() == null || request.getName().isEmpty() || request.getName().isBlank()) throw new BadRequestException("Name is required");

        FileEntity data = this.fileRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found!"));

        data.setOriginalName(request.getName());

        try {
            return this.fileRepository.save(data);
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    public FileEntity delete(Long id) {
        FileEntity data = this.fileRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException("Not Found!"));

        data.setDeletedAt(new Date());

        try {
            return this.fileRepository.save(data);
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    public FileEntity deleteFromTrash(Long id) {
        FileEntity data = this.fileRepository.findByIdAndDeletedAtIsNotNull(id).orElseThrow(() -> new NotFoundException("Not Found!"));

        try {
            this.storageService.deleteFile(data.getName());

            this.fileRepository.delete(data);

            return data;
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    public FileEntity forceDelete(Long id) {
        FileEntity data = this.fileRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found!"));

        try {
            this.storageService.deleteFile(data.getName());

            this.fileRepository.delete(data);

            return data;
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
