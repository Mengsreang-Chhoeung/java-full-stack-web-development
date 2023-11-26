package com.ms.crud_api.controller.backend;

import com.ms.crud_api.model.entity.PostCommentEntity;
import com.ms.crud_api.model.request.post_comment.CreatePostCommentRequest;
import com.ms.crud_api.model.request.post_comment.UpdatePostCommentRequest;
import com.ms.crud_api.model.response.post_comment.PostCommentResponse;
import com.ms.crud_api.model.response.post_comment.ShortPostCommentResponse;
import com.ms.crud_api.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post-comment")
public class PostCommentController {
    private final PostCommentService postCommentService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @PostMapping
    public ResponseEntity<ShortPostCommentResponse> create(@RequestBody CreatePostCommentRequest req) throws Exception {
        PostCommentEntity data = this.postCommentService.create(req);

        return ResponseEntity.ok(ShortPostCommentResponse.fromEntity(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShortPostCommentResponse> update(@PathVariable Long id, @RequestBody UpdatePostCommentRequest req) throws Exception {
        PostCommentEntity data = this.postCommentService.update(id, req);

        return ResponseEntity.ok(ShortPostCommentResponse.fromEntity(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShortPostCommentResponse> delete(@PathVariable Long id) throws Exception {
        PostCommentEntity data = this.postCommentService.delete(id);

        return ResponseEntity.ok(ShortPostCommentResponse.fromEntity(data));
    }

    @GetMapping
    public ResponseEntity<List<PostCommentResponse>> findAll(@RequestParam(required = false) Long postId) {
        List<PostCommentResponse> data = this.postCommentService.findAll(postId).stream().map(PostCommentResponse::fromEntity).toList();

        return ResponseEntity.ok(data);
    }
}
