package com.zsc.edu.bill.exception;

//import com.zsc.study.module.common.domain.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author harry_yao
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler {

    @Resource
    private ObjectMapper objectMapper;
    @ExceptionHandler(value = {ConstraintException.class})
    public ResponseEntity<Object> handleException(ConstraintException ex) throws JsonProcessingException {
        log.error("ConstraintException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotExistException.class})
    public ResponseEntity<Object> handleException(NotExistException ex) throws JsonProcessingException {
        log.error("NotExistException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StateException.class})
    public ResponseEntity<Object> handleException(StateException ex) throws JsonProcessingException {
        log.error("StateException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StorageException.class})
    public ResponseEntity<Object> handleException(StorageException ex) throws JsonProcessingException {
        log.error("StorageException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserHasNoIdentityException.class})
    public ResponseEntity<Object> handleException(UserHasNoIdentityException ex) throws JsonProcessingException {
        log.error("UserHasNoIdentityException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ValidateException.class})
    public ResponseEntity<Object> handleException(ValidateException ex) throws JsonProcessingException {
        log.error("ValidateException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleException(ApiException ex) throws JsonProcessingException {
        log.error("ApiException: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) throws JsonProcessingException {
        log.error("Exception: {}", objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())));
        return new ResponseEntity<>(objectMapper.writeValueAsString(Map.of("msg", ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
