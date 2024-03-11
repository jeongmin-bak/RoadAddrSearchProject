package org.didim365.hw1.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import org.didim365.hw1.annotation.validation.PasswordValidator;

@Target(ElementType.FIELD) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
@Constraint(validatedBy = PasswordValidator.class) // 3
public @interface Password {
    String message() default "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
    Class[] groups() default {};

    Class[] payload() default {};
}
