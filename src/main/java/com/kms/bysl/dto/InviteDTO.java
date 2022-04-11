package com.kms.bysl.dto;

import java.util.Date;

public class InviteDTO {
	private int id;
	private String email;
	private String inviteKey;
	private Integer senderId;
	private String sender;
	private Integer workspaceId;
	private boolean isAccepted;
	private Date createdAt;
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInviteKey() {
		return inviteKey;
	}
	public void setInviteKey(String inviteKey) {
		this.inviteKey = inviteKey;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(Integer workspaceId) {
		this.workspaceId = workspaceId;
	}
	public boolean isAccepted() {
		return isAccepted;
	}
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
