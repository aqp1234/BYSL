package com.kms.bysl;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseData {
	private StatusEnum status = StatusEnum.OK;
	private String message;
	private Object data;
	
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public ResponseEntity<ResponseData> getResponseEntity(HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<>(this, headers, httpStatus);
	}
	
	public ResponseEntity<ResponseData> getResponseEntity(HttpStatus httpStatus, String location){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		try {
			headers.setLocation(new URI(location));
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(this, headers, httpStatus);
	}
	
	public ResponseEntity<ResponseData> get201ResponseEntity(String contentLocation){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("Content-Location", contentLocation);
		
		return new ResponseEntity<>(this, headers, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseData> get201ResponseEntity(String contentLocation, String location){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("Content-Location", contentLocation);
		try {
			headers.setLocation(new URI(location));
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(this, headers, HttpStatus.CREATED);
	}
}