package com.example.ondream.models;

import com.google.gson.annotations.SerializedName;

public class MMention {
	@SerializedName("mention_id")
	private String id;
	
	@SerializedName("name")
	private String name;

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
	
	
}
