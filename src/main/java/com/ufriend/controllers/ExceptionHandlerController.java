package com.ufriend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ufriend.dto.errors.ErrorModelDTO;
import com.ufriend.dto.errors.ErrorResponseDTO;
import com.ufriend.dto.http.ResponseDTO;

@ControllerAdvice
public class ExceptionHandlerController {

    // Error Validating
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(
            HttpServletRequest req,
            MethodArgumentNotValidException ex) {

        List<ErrorModelDTO> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModelDTO(err.getField(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(
                        false,
                        "Send a valid body",
                        ErrorResponseDTO.builder().errors(errorMessages).build()));
    }

    // No body sent
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> httpMessageNotReadableException(
            HttpServletRequest req,
            HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, "Body not sent", null));

    }
}
