package com.iuri.nfe.application.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iuri.nfe.application.exception.NotaFiscalNotFoundException;

@ControllerAdvice
public class NotaFiscalExceptionHandler {

    @ExceptionHandler(NotaFiscalNotFoundException.class)
    public ResponseEntity<String> handleNotaFiscalNotFoundException(NotaFiscalNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
