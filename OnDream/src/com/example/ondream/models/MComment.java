package com.example.ondream.models;

import com.google.gson.annotations.SerializedName;

public class MComment {
	@SerializedName("id")
	private String id;
	
	@SerializedName("id")
	private String author;
	
	@SerializedName("id")
	private String content;
	
	@SerializedName("created_at")
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
