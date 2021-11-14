package com.windowsxp.bookhub.backend.entity;

import com.windowsxp.bookhub.backend.enumerate.UserType;
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
    @Column(columnDefinition = "MEDIUMINT")
    private Integer userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private UserType userType;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    public interface Username{
        String getUsername();
    }
}