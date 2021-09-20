package com.windowsxp.bookstore.dto.request;

import lombok.Getter;

@Getter
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String address;
}
