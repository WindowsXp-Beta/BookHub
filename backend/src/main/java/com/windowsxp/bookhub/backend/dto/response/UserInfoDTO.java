package com.windowsxp.bookhub.backend.dto.response;

import com.windowsxp.bookhub.backend.enumerate.UserType;
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
