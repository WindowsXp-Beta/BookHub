package com.windowsxp.bookhub.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookhub.backend.constant.Constant;
import com.windowsxp.bookhub.backend.dto.request.LoginDTO;
import com.windowsxp.bookhub.backend.dto.request.RegisterDTO;
import com.windowsxp.bookhub.backend.dto.request.UserTypeEditDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.dto.response.UserInfoDTO;
import com.windowsxp.bookhub.backend.entity.User;
import com.windowsxp.bookhub.backend.service.UserService;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.backend.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;

    private final UserService userService;


    @PostMapping("/login")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            LogUtil.debug(loginDTO.getUsername()+'\t'+loginDTO.getPassword());
            User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
            setSession(user);
            return ResponseEntity.ok().body(modelMapper.map(user, UserInfoDTO.class));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> logout() {
        Boolean status = SessionUtil.removeSession();

        if (status) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("登出失败，请勿重复操作");
    }

    @GetMapping("/session")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> checkSession() {
        JSONObject auth = SessionUtil.getAuth();
        LogUtil.debug("check session");
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("登录失效");
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/admin/user")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> getUsers(@RequestParam int page,
                                      @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(new PageDTO<>(userService.findAllUsers(PageRequest.of(page, pageSize))));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/admin/user/{id}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/admin/user")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> editUser(@RequestBody UserTypeEditDTO userTypeEditDTO) {
        try {
            userService.editUser(userTypeEditDTO.getUserId(), userTypeEditDTO.getUserType());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user/name")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> checkDuplicateUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok().body(userService.checkDuplicateUsername(username));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/user")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {
            LogUtil.debug("register with "+registerDTO.toString());
            User user = userService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(), registerDTO.getAddress());
            setSession(user);
            LogUtil.debug(user.toString());
            UserInfoDTO userInfoDTO =  modelMapper.map(user, UserInfoDTO.class);
            LogUtil.debug(userInfoDTO.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoDTO);
        } catch (RuntimeException e) {
            LogUtil.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void setSession(User user) {
        JSONObject obj = new JSONObject();
        obj.put(Constant.USER_ID, user.getUserId());
        obj.put(Constant.USERNAME, user.getUsername());
        obj.put(Constant.USER_TYPE, user.getUserType());
        SessionUtil.setSession(obj);
    }
}