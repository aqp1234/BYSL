package com.kms.bysl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum StatusEnum {
	OK(200, "OK"),
	CREATED(201, "CREATED"),
	NO_CONTENT(204, "NO_CONTENT"),
	BAD_REQUEST(400, "BAD_REQUEST"),
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	FORBIDDEN(403, "FORBIDDEN"),
	NOT_FOUND(404, "NOT_FOUND"),
	INTERNAL_SERER_ERROR(500, "INTERNAL_SERER_ERROR");
	
	int statusCode;
	String code;
	
	StatusEnum(int statusCode, String code){
		this.statusCode = statusCode;
		this.code = code;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
