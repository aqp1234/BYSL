package com.kms.bysl.exception;

public class NullSoloWorkspaceException extends RuntimeException{

	public NullSoloWorkspaceException(Exception e, String msg) {
		super(msg);
	}
}
