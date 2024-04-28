package br.rpe.challenger.view.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionDetails {
    private String timestamp;
    private int status;
    private String exception;
    private Map<String, Object> errors;
}
