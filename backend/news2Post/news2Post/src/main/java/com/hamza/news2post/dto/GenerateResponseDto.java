package com.hamza.news2post.dto;

import java.util.List;

public class GenerateResponseDto {
	private List<String> hooks;
    private String caption;
    private String imagePrompt;

    public GenerateResponseDto() {}

    public GenerateResponseDto(List<String> hooks, String caption, String imagePrompt) {
        this.hooks = hooks;
        this.caption = caption;
        this.imagePrompt = imagePrompt;
    }

    public List<String> getHooks() { return hooks; }
    public void setHooks(List<String> hooks) { this.hooks = hooks; }
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    public String getImagePrompt() { return imagePrompt; }
    public void setImagePrompt(String imagePrompt) { this.imagePrompt = imagePrompt; }
}


