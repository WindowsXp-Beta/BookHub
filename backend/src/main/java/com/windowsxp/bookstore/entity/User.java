package com.windowsxp.bookstore.entity;

import com.windowsxp.bookstore.enumerate.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    private String email;

    private String address;

    public interface Username{
        String getUsername();
    }
}