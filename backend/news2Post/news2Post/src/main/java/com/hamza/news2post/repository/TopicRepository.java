package com.hamza.news2post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamza.news2post.entity.TopicEntity;

public interface TopicRepository extends JpaRepository <TopicEntity,Long> {
	Optional<TopicEntity> findFirstByTopic(String topic);
}
