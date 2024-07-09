package br.com.senac.BackEndEstoque.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ErrorPayload {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message = null;
    private List<FieldMessage> fieldMessages = null;
    private String path;

    @Builder
    @Data
    static class FieldMessage {
        private String field;
        private String message;
    }
}