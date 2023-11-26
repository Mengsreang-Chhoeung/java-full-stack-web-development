package com.ms.crud_api.repository;

import com.ms.crud_api.model.entity.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {
    List<PostCommentEntity> findAllByPostId(Long postId);
}
