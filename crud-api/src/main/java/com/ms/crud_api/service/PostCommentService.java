package com.ms.crud_api.service;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.PostCommentEntity;
import com.ms.crud_api.model.entity.PostEntity;
import com.ms.crud_api.model.request.post_comment.CreatePostCommentRequest;
import com.ms.crud_api.model.request.post_comment.UpdatePostCommentRequest;
import com.ms.crud_api.repository.PostCommentRepository;
import com.ms.crud_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository, PostRepository postRepository) {
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    public PostCommentEntity create(CreatePostCommentRequest req) throws Exception {
        // prepare request model to entity model
        PostCommentEntity request = req.toEntity();

        // validate that post exist from db or not
        PostEntity foundPost = this.postRepository.findById(req.getPostId()).orElseThrow(() -> new NotFoundException("Post not found!"));

        // then set post to post comments entity model
        request.setPost(foundPost);

        try {
            // save record
            return this.postCommentRepository.save(request);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public PostCommentEntity update(Long id, UpdatePostCommentRequest req) throws Exception {
        // validate first with id of comment exist in db or not
        PostCommentEntity foundData = this.postCommentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found!"));

        // prepare data and update data
        foundData.setComment(req.getComment());

        try {
            // save to db
            return this.postCommentRepository.save(foundData);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public PostCommentEntity delete(Long id) throws Exception{
        // validate first with id of comment exist in db or not
        PostCommentEntity foundData = this.postCommentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found!"));

        try {
            // delete
            this.postCommentRepository.deleteById(foundData.getId());
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        return foundData;
    }

    public List<PostCommentEntity> findAll(Long postId) {
        if (postId == null) return this.postCommentRepository.findAll();
        else return this.postCommentRepository.findAllByPostId(postId);
    }
}
