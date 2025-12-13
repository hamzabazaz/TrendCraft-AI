package com.hamza.news2post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamza.news2post.entity.ArticleEntity;

public interface ArticleRepository  extends JpaRepository<ArticleEntity,Long>{
	List<ArticleEntity> findByTopic_TopicOrderByPublishedAtDesc(String topic);
}
