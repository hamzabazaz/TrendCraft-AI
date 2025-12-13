package com.hamza.news2post.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamza.news2post.dto.GenerateRequestDto;
import com.hamza.news2post.dto.GenerateResponseDto;
import com.hamza.news2post.entity.GeneratedOutputEntity;
import com.hamza.news2post.repository.GeneratedOutputRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GenerateService {

    private final WebClient webClient;
    private final String groqApiKey;
    private final String model;
    private final ObjectMapper mapper = new ObjectMapper();
    private final GeneratedOutputRepository generatedOutputRepository;

    public GenerateService(
            @Value("${app.groq.apiKey}") String groqApiKey,
            @Value("${app.groq.model:llama-3.1-8b-instant}") String model,
            GeneratedOutputRepository generatedOutputRepository
    ) {
        this.groqApiKey = groqApiKey;
        this.model = model;
        this.generatedOutputRepository = generatedOutputRepository;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .defaultHeader("Authorization", "Bearer " + groqApiKey)
                .build();
    }

    public GenerateResponseDto generate(GenerateRequestDto req) throws Exception {

        String prompt = buildPrompt(req);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);

        List<Map<String, String>> messages = List.of(
                Map.of("role", "system",
                        "content", "You generate hooks, captions, and image prompts. Respond ONLY in JSON."),
                Map.of("role", "user", "content", prompt)
        );

        body.put("messages", messages);
        body.put("max_tokens", 500);
        body.put("temperature", 0.7);

        String apiResponse = webClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode root = mapper.readTree(apiResponse);
        String content = root.at("/choices/0/message/content").asText();

        GenerateResponseDto dto = parseModelOutput(content);

        // Save DB
        String hooksJson = mapper.writeValueAsString(dto.getHooks());
        GeneratedOutputEntity entity = new GeneratedOutputEntity(
                req.getTopic(),
                hooksJson,
                dto.getCaption(),
                dto.getImagePrompt());
        generatedOutputRepository.save(entity);

        return dto;
    }

    private String buildPrompt(GenerateRequestDto req) {
        StringBuilder sb = new StringBuilder();
        sb.append("Topic: ").append(req.getTopic()).append("\n\nArticles:\n");

        int i = 1;
        for (GenerateRequestDto.ArticleInput a : req.getArticles()) {
            sb.append(i++).append(". ").append(a.getTitle()).append("\n");
            sb.append("Snippet: ").append(a.getSnippet()).append("\n\n");
        }

        sb.append("""
                
                Return ONLY valid JSON:
                {
                  "hooks": ["h1", "h2", "h3"],
                  "caption": "short caption",
                  "imagePrompt": "detailed prompt"
                }
                """);

        return sb.toString();
    }

    private GenerateResponseDto parseModelOutput(String content) throws Exception {
        JsonNode n = mapper.readTree(content);

        List<String> hooks = new ArrayList<>();
        if (n.has("hooks")) {
            n.get("hooks").forEach(h -> hooks.add(h.asText()));
        }

        while (hooks.size() < 3) hooks.add("...");

        String caption = n.has("caption") ? n.get("caption").asText() : "";
        String image = n.has("imagePrompt") ? n.get("imagePrompt").asText() : "";

        return new GenerateResponseDto(hooks, caption, image);
    }
}
