package com.ms.crud_api.service;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.PostEntity;
import com.ms.crud_api.model.request.post.PostRequest;
import com.ms.crud_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity create(PostRequest req) throws Exception {
        try {
            return this.postRepository.save(req.toEntity());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public PostEntity update(Long id, PostRequest req) throws Exception {
        PostEntity found = this.postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found!"));

        found.setTitle(req.getTitle());
        found.setDescription(req.getDescription());

        try {
            return this.postRepository.save(found);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public PostEntity findOne(Long id) throws NotFoundException {
        return this.postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found!"));
    }

    public List<PostEntity> findAll() {
        return this.postRepository.findAll();
    }

    public PostEntity delete(Long id) throws Exception {
        PostEntity found = this.findOne(id);

        try {
            this.postRepository.deleteById(found.getId());
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return found;
    }
}
