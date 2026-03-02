package com.restful.aiagent.entities;

public class PageDto {
	private String id;
    private String content_preview;
    private int page;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent_preview() {
		return content_preview;
	}
	public void setContent_preview(String content_preview) {
		this.content_preview = content_preview;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
