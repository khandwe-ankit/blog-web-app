package com.ankit.blog.exceptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ankit.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(new ApiResponse(ex.getMessage(), false), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<List<Map<String, String >>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<Map<String, String >> errorList = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			LinkedHashMap<String, String> resMap = new LinkedHashMap<>();
			String field = ((FieldError)e).getField();
			String defaultMessage = e.getDefaultMessage();
			resMap.put("field", field);
			resMap.put("message", defaultMessage);
			errorList.add(resMap);
		});
		return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
	}
}
