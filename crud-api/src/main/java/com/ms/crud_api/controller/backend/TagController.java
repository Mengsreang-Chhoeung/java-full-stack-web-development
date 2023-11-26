package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.TagEntity;
import com.ms.crud_api.model.request.tag.TagRequest;
import com.ms.crud_api.model.response.tag.TagResponse;
import com.ms.crud_api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<TagResponse> create(@RequestBody TagRequest request) throws Exception {
        TagEntity tag = this.tagService.create(request);

        return ResponseEntity.ok(TagResponse.fromEntity(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> update(@PathVariable Long id, @RequestBody TagRequest request) throws Exception {
        TagEntity tag = this.tagService.update(id, request);

        return ResponseEntity.ok(TagResponse.fromEntity(tag));
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> findAll() {
        List<TagResponse> category = this.tagService.findAll().stream().map(TagResponse::fromEntity).toList();

        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> findOne(@PathVariable Long id) throws NotFoundException {
        TagEntity tag = this.tagService.findOne(id);

        return ResponseEntity.ok(TagResponse.fromEntity(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagResponse> delete(@PathVariable Long id) throws Exception {
        TagEntity tag = this.tagService.delete(id);

        return ResponseEntity.ok(TagResponse.fromEntity(tag));
    }
}
