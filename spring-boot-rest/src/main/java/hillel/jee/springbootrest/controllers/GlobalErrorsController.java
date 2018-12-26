package hillel.jee.springbootrest.controllers;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorsController {
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    return ResponseEntity
        .status(INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(new ErrorResponse(e.getMessage()));
  }

  @Data
  @AllArgsConstructor
  class ErrorResponse {
    String errorMessage;
  }
}
