package com.restful.aiagent.entities;

import java.util.List;

public class DocumentDto {

	private String file_name;
    private int total_pages;
    private List<PageDto> pages;
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public List<PageDto> getPages() {
		return pages;
	}
	public void setPages(List<PageDto> pages) {
		this.pages = pages;
	}
    
	
}
