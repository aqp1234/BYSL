package com.kms.bysl.exception;

public class DuplicateUserWorkspaceException extends RuntimeException{
	
	public DuplicateUserWorkspaceException(Exception e, String msg) {
		super(msg);
	}
}
