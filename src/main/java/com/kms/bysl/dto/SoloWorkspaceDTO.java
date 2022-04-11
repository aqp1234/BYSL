package com.kms.bysl.dto;

import java.util.Date;

public class SoloWorkspaceDTO {
	private int id;
	private String soloWorkspaceName;
	private int ownerId;
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSoloWorkspaceName() {
		return soloWorkspaceName;
	}
	public void setSoloWorkspaceName(String soloWorkspaceName) {
		this.soloWorkspaceName = soloWorkspaceName;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
