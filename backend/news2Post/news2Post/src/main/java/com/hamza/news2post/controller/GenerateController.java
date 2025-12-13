package com.hamza.news2post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamza.news2post.dto.GenerateRequestDto;
import com.hamza.news2post.dto.GenerateResponseDto;
import com.hamza.news2post.service.GenerateService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GenerateController {
	 private final GenerateService generateService;

	    public GenerateController(GenerateService generateService) { this.generateService = generateService; }

	    @PostMapping("/generate")
	    public ResponseEntity<?> generate(@RequestBody GenerateRequestDto req) {
	        if (req == null || req.getTopic() == null || req.getTopic().trim().isEmpty()) {
	            return ResponseEntity.badRequest().body(Map.of("error", "topic is required"));
	        }
	        if (req.getArticles() == null || req.getArticles().isEmpty()) {
	            return ResponseEntity.badRequest().body(Map.of("error", "at least one article is required"));
	        }
	        try {
	            GenerateResponseDto out = generateService.generate(req);
	            return ResponseEntity.ok(out);
	        } catch (Exception ex) {
	            return ResponseEntity.status(500).body(Map.of("error", "Generation failed", "details", ex.getMessage()));
	        }


}
}
