package com.windowsxp.bookstore.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String address;
}
