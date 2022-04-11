package com.kms.bysl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kms.bysl.ResponseData;
import com.kms.bysl.StatusEnum;
import com.kms.bysl.exception.DuplicateUserWorkspaceException;
import com.kms.bysl.exception.NullObjectException;
import com.kms.bysl.exception.NullSoloWorkspaceException;
import com.kms.bysl.exception.NullUserWorkspaceException;
import com.kms.bysl.exception.NullWorkspaceException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(value=DuplicateUserWorkspaceException.class)
	public ResponseEntity<ResponseData> handlerPostException(Exception e){
		e.printStackTrace();
		ResponseData responseData = new ResponseData();
		responseData.setMessage(e.getMessage());
		responseData.setStatus(StatusEnum.BAD_REQUEST);
		return responseData.getResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {NullWorkspaceException.class, NullUserWorkspaceException.class, NullSoloWorkspaceException.class, NullObjectException.class})
	public String handlerGetException(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("errorMsg", e.getMessage());
		return "error";
	}
}
