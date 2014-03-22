package com.example.ondream.models;

import com.google.gson.annotations.SerializedName;

public class MUser {
	@SerializedName("id")
	private String id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("status")
	private String status;
	
	@SerializedName("sleep_time")
	private String sleepTime;
	
	@SerializedName("wakeup_time")
	private String wakeupTime;
	
	@SerializedName("created_at")
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getWakeupTime() {
		return wakeupTime;
	}

	public void setWakeupTime(String wakeupTime) {
		this.wakeupTime = wakeupTime;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
