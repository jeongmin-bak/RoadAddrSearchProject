package org.didim365.hw1.exception.user;

public class DuplicateEmailException extends IllegalArgumentException {
    public static final String errorMsg = "이미 가입된 이메일입니다.";

    public DuplicateEmailException() {
        super(errorMsg);
    }

}
