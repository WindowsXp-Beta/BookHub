package com.windowsxp.bookstore.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UserException extends RuntimeException{
    private final UserExceptionType userExceptionType;

    public enum UserExceptionType {
        DUPLICATE_USERNAME, BANNED_USER, UNAUTHORIZED_USER, WRONG_PASSWORD
    }

    public static final Map<UserExceptionType, String> map = new HashMap<>() {{
        put(UserExceptionType.DUPLICATE_USERNAME, "用户名重复");
        put(UserExceptionType.UNAUTHORIZED_USER, "用户不存在");
        put(UserExceptionType.BANNED_USER, "用户被禁用");
        put(UserExceptionType.WRONG_PASSWORD, "密码错误");
    }};

    public UserException(UserExceptionType userExceptionType) {
        super(map.get(userExceptionType));
        this.userExceptionType = userExceptionType;
    }
}
