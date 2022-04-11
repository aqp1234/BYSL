package com.kms.bysl.dto;

import java.sql.Timestamp;

public class SelfIntroduceCommentDTO {
	private int id;
	private int selfIntroduceId;
	private Integer commenterId;
	private String commenter;
	private String comment;
	private boolean isDeleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSelfIntroduceId() {
		return selfIntroduceId;
	}
	public void setSelfIntroduceId(int selfIntroduceId) {
		this.selfIntroduceId = selfIntroduceId;
	}
	public Integer getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(Integer commenterId) {
		this.commenterId = commenterId;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
