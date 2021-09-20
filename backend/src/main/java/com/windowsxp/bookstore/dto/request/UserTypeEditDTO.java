package com.windowsxp.bookstore.dto.request;

import com.windowsxp.bookstore.enumerate.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTypeEditDTO {
    private Integer userId;
    private UserType userType;
}
