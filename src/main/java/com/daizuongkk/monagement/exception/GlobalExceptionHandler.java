package com.daizuongkk.monagement.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)

  public ResponseEntity<ErrorResponse> handleAppException(AppException exception, WebRequest request) {

    ErrorResponse response = buildErrorCodeResponse(exception.getErrorCode(), request);

    return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(response);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException e, WebRequest request) {

    BindingResult bindingResult = e.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    List<String> errors = fieldErrors.stream()
        .map(FieldError::getDefaultMessage)
        .toList();

    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(errors.size() > 1 ? errors.toString() : errors.get(0))
        .path(request.getDescription(false).replace("uri=", ""))
        .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(
      BadCredentialsException exception, WebRequest request) {

    log.error("Authentication failed: {}", exception.getMessage());
    ErrorResponse response = buildErrorCodeResponse(ErrorCode.INVALID_CREDENTIALS, request);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(
      MissingRequestHeaderException exception, WebRequest request) {

    ErrorResponse response = ErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message("Required header '" + exception.getHeaderName() + "' is missing")
        .path(request.getDescription(false).replace("uri=", ""))
        .build();

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception, WebRequest request) {
    ErrorCode error = ErrorCode.UNCATEGORIZED_EXCEPTION;

    ErrorResponse response = buildErrorCodeResponse(error, request);
    log.error("Unexpected exception occurred: ", exception);
    return ResponseEntity.status(error.getHttpStatus()).body(response);

  }

  private ErrorResponse buildErrorCodeResponse(ErrorCode errorCode, WebRequest request) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .error(errorCode.getHttpStatus().getReasonPhrase())
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
  }
}
