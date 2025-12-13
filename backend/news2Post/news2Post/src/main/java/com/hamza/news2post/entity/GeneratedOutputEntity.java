package com.hamza.news2post.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "generated_outputs")
public class GeneratedOutputEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Lob
    @Column(name = "hooks_json", columnDefinition = "TEXT")
    private String hooksJson;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String caption;

    @Lob
    @Column(name = "image_prompt", columnDefinition = "TEXT")
    private String imagePrompt;

    private Instant createdAt = Instant.now();

    public GeneratedOutputEntity() {}

    public GeneratedOutputEntity(String topic, String hooksJson, String caption, String imagePrompt) {
        this.topic = topic;
        this.hooksJson = hooksJson;
        this.caption = caption;
        this.imagePrompt = imagePrompt;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getHooksJson() { return hooksJson; }
    public void setHooksJson(String hooksJson) { this.hooksJson = hooksJson; }
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    public String getImagePrompt() { return imagePrompt; }
    public void setImagePrompt(String imagePrompt) { this.imagePrompt = imagePrompt; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}


