package com.hamza.news2post.dto;

import java.util.List;

public class GenerateRequestDto {
	private String topic;
    private List<ArticleInput> articles;

    public GenerateRequestDto() {}

    public GenerateRequestDto(String topic, List<ArticleInput> articles) {
        this.topic = topic;
        this.articles = articles;
    }

    public static class ArticleInput {
        private String title;
        private String snippet;
        private String url;

        public ArticleInput() {}

        public ArticleInput(String title, String snippet, String url) {
            this.title = title;
            this.snippet = snippet;
            this.url = url;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSnippet() { return snippet; }
        public void setSnippet(String snippet) { this.snippet = snippet; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public List<ArticleInput> getArticles() { return articles; }
    public void setArticles(List<ArticleInput> articles) { this.articles = articles; }
}


