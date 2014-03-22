package com.example.ondream.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MDream {
	@SerializedName("id")
	private String id;
	
	@SerializedName("author")
	private String author;
	
	@SerializedName("content")
	private String content;
	
	@SerializedName("privilege")
	private String privilege;
	
	private List<MComment> comments;
	
	private List<MTag> tags;
	
	private List<MMention> mention;
	
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

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public List<MComment> getComments() {
		return comments;
	}

	public void setComments(List<MComment> comments) {
		this.comments = comments;
	}

	public List<MTag> getTags() {
		return tags;
	}

	public void setTags(List<MTag> tags) {
		this.tags = tags;
	}

	public List<MMention> getMention() {
		return mention;
	}

	public void setMention(List<MMention> mention) {
		this.mention = mention;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	
}
