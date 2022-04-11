package com.kms.bysl.exception;

public class NullObjectException extends RuntimeException{
	
	public NullObjectException(Exception e, String msg) {
		super(msg);
	}
}
