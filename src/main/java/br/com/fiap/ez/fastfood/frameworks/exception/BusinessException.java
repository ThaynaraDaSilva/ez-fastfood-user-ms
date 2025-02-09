package br.com.fiap.ez.fastfood.frameworks.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}