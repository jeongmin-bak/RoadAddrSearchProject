package org.didim365.hw1.exception.user;

public class DuplicateUserIdException extends IllegalArgumentException {
    public static final String errorMsg = "중복된 아이디입니다..";

    public DuplicateUserIdException() {
        super(errorMsg);
    }

}
