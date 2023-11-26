package com.ms.crud_api.service;

import com.ms.crud_api.exception.AlreadyExistException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.TagEntity;
import com.ms.crud_api.model.request.tag.TagRequest;
import com.ms.crud_api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagEntity create(TagRequest request) throws Exception {
        // prepare request to entity
        TagEntity data = request.toEntity();

        // check name from request exists or not in db
        if (this.tagRepository.existsByName(data.getName()))
            throw new AlreadyExistException("Tag name already exists!");

        try {
            // save entity
            return this.tagRepository.save(data);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public TagEntity update(Long id, TagRequest request) throws Exception {
        // check from data from database and if it isn't exist then throw error
        TagEntity foundData = this.tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found!"));

        if (!Objects.equals(foundData.getName(), request.getName()))
            if (this.tagRepository.existsByName(request.getName()))
                throw new AlreadyExistException("Tag name already exists!");

        // add request data to existing data
        foundData.setName(request.getName());

        try {
            // update entity
            return this.tagRepository.save(foundData);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<TagEntity> findAll() {
        return this.tagRepository.findAll();
    }

    public TagEntity findOne(Long id) throws NotFoundException {
        return this.tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found"));
    }

    public TagEntity delete(Long id) throws Exception {
        // get tag data from db by id
        TagEntity tag = this.findOne(id);

        try {
            // if everything work well then delete
            this.tagRepository.deleteById(tag.getId());
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        // return data
        return tag;
    }
}
