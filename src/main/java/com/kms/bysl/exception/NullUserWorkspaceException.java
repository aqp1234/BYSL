package com.kms.bysl.exception;

public class NullUserWorkspaceException extends RuntimeException{

	public NullUserWorkspaceException(Exception e, String msg) {
		super(msg);
	}
}
