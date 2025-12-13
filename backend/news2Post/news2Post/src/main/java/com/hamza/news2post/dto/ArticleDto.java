package com.hamza.news2post.dto;

import java.time.Instant;

import com.hamza.news2post.entity.ArticleEntity;

public class ArticleDto {
	private Long id;
    private String title;
    private String source;
    private String url;
    private String snippet;
    private Instant publishedAt;

    public ArticleDto() {}
    
    

	public ArticleDto(Long id, String title, String source, String url, String snippet, Instant publishedAt) {
		super();
		this.id = id;
		this.title = title;
		this.source = source;
		this.url = url;
		this.snippet = snippet;
		this.publishedAt = publishedAt;
	}
	public static ArticleDto fromEntity(ArticleEntity e) {
        if (e == null) return null;
        return new ArticleDto(
                e.getId(),
                e.getTitle(),
                e.getSource(),
                e.getUrl(),
                e.getSnippet(),
                e.getPublishedAt()
        );
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}
    
    

}
