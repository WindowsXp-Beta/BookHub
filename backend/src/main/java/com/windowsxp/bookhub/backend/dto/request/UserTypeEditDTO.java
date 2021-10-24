package com.windowsxp.bookhub.backend.dto.request;

import com.windowsxp.bookhub.backend.enumerate.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTypeEditDTO {
    private Integer userId;
    private UserType userType;
}
