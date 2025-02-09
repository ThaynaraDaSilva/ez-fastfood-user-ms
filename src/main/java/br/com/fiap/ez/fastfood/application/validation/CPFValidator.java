package br.com.fiap.ez.fastfood.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if(cpf == null || !Pattern.matches(CPF_REGEX, cpf)) {
            return false;
        }
        return true; // Aqui você pode adicionar mais regras de validação, se necessário.
    }
}
