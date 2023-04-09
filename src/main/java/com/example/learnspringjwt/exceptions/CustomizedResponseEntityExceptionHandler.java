package com.example.learnspringjwt.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDetails> handleAllExceptions(Exception ignoredEx, WebRequest ignoredRequest) {
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails
                .setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        responseDetails
                .setResponseMessage("Something went wrong" + ignoredEx.getMessage());

        return new ResponseEntity<>(responseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDetails> handleNullException(NullPointerException ignoredEx) {
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails
                .setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        responseDetails
                .setResponseMessage("User cannot be found");

        return new ResponseEntity<>(responseDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ResponseDetails> baseResponse(InternalAuthenticationServiceException ignoredE) {
        ResponseDetails resp = new ResponseDetails();
        resp.setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        resp.setResponseMessage("Cannot find the email associated");
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDetails> baseResponse(DataIntegrityViolationException e) {
        ResponseDetails resp = new ResponseDetails();
        resp.setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        resp.setResponseMessage(Objects.requireNonNull(e.getRootCause()).getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseDetails> baseResponse(NoSuchElementException ignoredE) {
        ResponseDetails resp = new ResponseDetails();
        resp.setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        resp.setResponseMessage("The database is empty ");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDetails> passwordResponse(BadCredentialsException ignoredE) {
        ResponseDetails resp = new ResponseDetails();
        resp.setResponseMessage("The password does not match");
        resp.setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ResponseDetails> passwordResponse(MalformedJwtException ignoredE, WebRequest ignoredRequest) {
        ResponseDetails resp = new ResponseDetails();
        resp.setResponseMessage("The password does not match");
        resp.setResponseStatus(ResponseDetails.ResponseStatusEnum.ERROR);
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }
}
