package com.hamza.news2post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamza.news2post.entity.GeneratedOutputEntity;

public interface GeneratedOutputRepository extends JpaRepository<GeneratedOutputEntity,Long> {
    List<GeneratedOutputEntity> findByTopicOrderByCreatedAtDesc(String topic);

}
