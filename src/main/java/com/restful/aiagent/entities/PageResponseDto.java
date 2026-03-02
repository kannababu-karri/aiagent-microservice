package com.restful.aiagent.entities;

import java.io.Serializable;
import java.util.List;

public class PageResponseDto<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    
    private int total_files;
    private List<T> documents;
    
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal_files() {
		return total_files;
	}
	public void setTotal_files(int total_files) {
		this.total_files = total_files;
	}
	public List<T> getDocuments() {
		return documents;
	}
	public void setDocuments(List<T> documents) {
		this.documents = documents;
	}
	
}
