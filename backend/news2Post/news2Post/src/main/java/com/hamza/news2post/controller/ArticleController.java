package com.hamza.news2post.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamza.news2post.dto.ArticleDto;
import com.hamza.news2post.service.ArticleService;

@RestController
@RequestMapping("/api")
public class ArticleController {
	
	private final ArticleService articleService;

    public ArticleController(ArticleService articleService) { this.articleService = articleService; }

	    @GetMapping("/articles")
	    public ResponseEntity<?> getArticles(@RequestParam(name = "topic", required = true) String topic) {
	        if (topic == null || topic.trim().isEmpty()) {
	            return ResponseEntity.badRequest().body(Map.of("error", "topic is required"));
	        }
	        try {
	            List<ArticleDto> list = articleService.fetchArticles(topic.trim(), 7);
	            Map<String,Object> resp = new HashMap<>();
	            resp.put("topic", topic.trim());
	            resp.put("articles", list);
	            return ResponseEntity.ok(resp);
	        } catch (Exception ex) {
	            return ResponseEntity.status(500).body(Map.of("error", "Failed to fetch articles", "details", ex.getMessage()));
	        }
	    }
	
}
