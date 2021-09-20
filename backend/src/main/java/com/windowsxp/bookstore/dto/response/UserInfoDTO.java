package com.windowsxp.bookstore.dto.response;

import com.windowsxp.bookstore.enumerate.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String password;
    private UserType userType;
}
