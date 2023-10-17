package br.com.gedielsonvieira.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Define classes globais no momento de tratamento de exceção, captura exceções
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(httpMessageNotReadableException.getMostSpecificCause().getMessage());
    }
}
