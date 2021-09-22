package com.windowsxp.bookstore.dto.response;

import com.windowsxp.bookstore.enumerate.UserType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoDTO {
    private String username;
    private UserType userType;
}
