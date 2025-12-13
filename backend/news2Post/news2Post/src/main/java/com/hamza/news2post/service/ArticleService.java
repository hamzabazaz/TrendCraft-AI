package com.hamza.news2post.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hamza.news2post.dto.ArticleDto;
import com.hamza.news2post.entity.ArticleEntity;
import com.hamza.news2post.entity.TopicEntity;
import com.hamza.news2post.repository.ArticleRepository;
import com.hamza.news2post.repository.TopicRepository;
import com.rometools.rome.feed.synd.SyndEntry;

@Service
public class ArticleService {

    private final RssService rssService;
    private final TopicRepository topicRepo;
    private final ArticleRepository articleRepo;

    public ArticleService(RssService rssService, TopicRepository topicRepo, ArticleRepository articleRepo) {
        this.rssService = rssService;
        this.topicRepo = topicRepo;
        this.articleRepo = articleRepo;
    }

    // 🔥 Add HTML cleaner here
    private String stripHtml(String input) {
        return input == null ? "" : input.replaceAll("<[^>]*>", "");
    }

    public List<ArticleDto> fetchArticles(String topic, int maxItems) throws Exception {

        TopicEntity topicEntity = topicRepo.findFirstByTopic(topic).orElseGet(() ->
            topicRepo.save(new TopicEntity(topic))
        );

        List<SyndEntry> entries = rssService.fetchGoogleNews(topic, maxItems);

        List<ArticleEntity> saved = entries.stream().map(e -> {

            ArticleEntity a = new ArticleEntity();

            // TITLE + URL
            a.setTitle(e.getTitle());
            a.setUrl(e.getLink());

            // SNIPPET (clean HTML)
            String snippet = "";
            try {
                if (e.getDescription() != null && e.getDescription().getValue() != null) {
                    snippet = e.getDescription().getValue();
                }
            } catch (Exception ignore) {}

            String cleanSnippet = stripHtml(snippet);  // 🔥 Clean snippet
            a.setSnippet(cleanSnippet);

            // SOURCE (safe, trimmed)
            String source = "Unknown";
            try {
                if (e.getAuthor() != null && !e.getAuthor().isBlank()) {
                    source = e.getAuthor();
                }
                else if (e.getSource() != null) {
                    String srcTitle = e.getSource().getTitle();
                    if (srcTitle != null && !srcTitle.isBlank()) {
                        source = srcTitle;
                    }
                }
            } catch (Exception ignore) {
                source = "Unknown";
            }

            // Trim to avoid DB overflow
            if (source.length() > 255) {
                source = source.substring(0, 255);
            }
            a.setSource(source);

            // PUBLISHED DATE
            if (e.getPublishedDate() != null) {
                a.setPublishedAt(e.getPublishedDate().toInstant());
            } else {
                a.setPublishedAt(Instant.now());
            }

            a.setTopic(topicEntity);

            return articleRepo.save(a);

        }).collect(Collectors.toList());

        return saved.stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
    }
}
