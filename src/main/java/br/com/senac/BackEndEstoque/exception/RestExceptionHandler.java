package br.com.senac.BackEndEstoque.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorPayload handleValidateException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        return ErrorPayload.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .fieldMessages(
                        exception.getBindingResult()
                                .getFieldErrors()
                                .stream().map(fieldError ->
                                        ErrorPayload.FieldMessage.builder()
                                                .field(fieldError.getField())
                                                .message(fieldError.getDefaultMessage())
                                                .build()
                                ).toList()
                )
                .path(request.getContextPath() + request.getServletPath())
                .build();
    }

    @ExceptionHandler(ClientRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorPayload handleClientRequestException(
            ClientRequestException exception,
            HttpServletRequest request
    ) {
        return ErrorPayload.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(exception.getLocalizedMessage())
                .path(request.getContextPath() + request.getServletPath())
                .build();
    }

}