package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.PostEntity;
import com.ms.crud_api.model.request.post.PostRequest;
import com.ms.crud_api.model.response.post.PostResponse;
import com.ms.crud_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest req) throws Exception {
        PostEntity data = this.postService.create(req);

        return ResponseEntity.ok(PostResponse.fromEntity(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody PostRequest req) throws Exception {
        PostEntity data = this.postService.update(id, req);

        return ResponseEntity.ok(PostResponse.fromEntity(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> delete(@PathVariable Long id) throws Exception {
        PostEntity data = this.postService.delete(id);

        return ResponseEntity.ok(PostResponse.fromEntity(data));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> post = this.postService.findAll().stream().map(PostResponse::fromEntity).toList();

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findOne(@PathVariable Long id) throws NotFoundException {
        PostEntity data = this.postService.findOne(id);

        return ResponseEntity.ok(PostResponse.fromEntity(data));
    }
}
