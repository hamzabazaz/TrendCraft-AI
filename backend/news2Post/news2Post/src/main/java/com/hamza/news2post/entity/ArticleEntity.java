package com.hamza.news2post.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id")
    private String externalId;

    private String title;

    @Column(length = 2048)
    private String url;

    @Column(length = 5000)
    private String snippet;

    @Column(length = 255)
    private String source;

    private Instant publishedAt;

    private Instant fetchedAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getSnippet() { return snippet; }
    public void setSnippet(String snippet) { this.snippet = snippet; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getFetchedAt() { return fetchedAt; }
    public void setFetchedAt(Instant fetchedAt) { this.fetchedAt = fetchedAt; }

    public TopicEntity getTopic() { return topic; }
    public void setTopic(TopicEntity topic) { this.topic = topic; }
}
