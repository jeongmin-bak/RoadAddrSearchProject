package org.didim365.hw1.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.didim365.hw1.annotation.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> { // 1

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { // 2
        if (value == null) {
            return false;
        }
        return value.matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}");
    }
}
