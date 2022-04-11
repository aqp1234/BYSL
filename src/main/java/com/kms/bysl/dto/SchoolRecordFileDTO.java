package com.kms.bysl.dto;

import java.sql.Timestamp;

public class SchoolRecordFileDTO {
	private int id;
	private int schoolRecordId;
	private String path;
	private String name;
	private String originalName;
	private Timestamp createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSchoolRecordId() {
		return schoolRecordId;
	}
	public void setSchoolRecordId(int schoolRecordId) {
		this.schoolRecordId = schoolRecordId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
