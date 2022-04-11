package com.kms.bysl.exception;

public class NullWorkspaceException extends RuntimeException{
	
	public NullWorkspaceException(Exception e, String msg) {
		super(msg);
	}
}
