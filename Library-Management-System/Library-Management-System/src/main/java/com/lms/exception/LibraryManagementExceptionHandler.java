package com.lms.exception;

import java.net.http.HttpHeaders;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lms.util.ResponseStructure;

@ControllerAdvice
public class LibraryManagementExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handlerAddressIdNotFoundException(Exception exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Other exception");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
	
	protected ResponseEntity<Object> handlerHttpRequestNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers,HttpStatusCode status,WebRequest request){
		
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(status.value());
		responseStructure.setMessage("request Method Not Supported");
		responseStructure.setData(ex.getMessage());
		return ResponseEntity.status(status).body(responseStructure);
	}
	
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingPathVariableException ex,
			HttpHeaders headers,HttpStatusCode status,WebRequest request){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(status.value());
		responseStructure.setMessage("Missing Path Variable");
		responseStructure.setData(ex.getMessage());
		return ResponseEntity.status(status).body(responseStructure);
	}
	
	
	protected ResponseEntity<Object> handlerTypeMismatch(TypeMismatchException ex,HttpHeaders headers,
			HttpStatusCode status,WebRequest request){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(status.value());
		responseStructure.setMessage("Data type Missmatched");
		responseStructure.setData(ex.getMessage());
		return ResponseEntity.status(status).body(responseStructure);
	}
	
	@ExceptionHandler(AddressIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlerAddressIdNotFoundException(AddressIdNotFoundException exception){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Other exception");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
