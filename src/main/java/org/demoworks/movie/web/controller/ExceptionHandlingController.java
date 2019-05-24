//package org.demoworks.movie.web.controller;
//
//import org.demoworks.movie.exceptions.DuplicateEntryException;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//@ControllerAdvice
//public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(value = {DuplicateEntryException.class})
//    public ResponseEntity<Object> filmAlreadyExists(Exception ex, WebRequest request){
//            String bodyOfResponse = "Duplicate Entry!";
//            return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//
//        }
//
//
//}
