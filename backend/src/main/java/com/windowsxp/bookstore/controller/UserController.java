package com.windowsxp.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.dto.request.LoginDTO;
import com.windowsxp.bookstore.dto.request.RegisterDTO;
import com.windowsxp.bookstore.dto.request.UserTypeEditDTO;
import com.windowsxp.bookstore.dto.response.UserInfoDTO;
import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.service.UserService;
import com.windowsxp.bookstore.utils.LogUtil;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<String> logout() {
        Boolean status = SessionUtil.removeSession();

        if (status) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("登出成功");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("登出失败，请勿重复操作");
    }

    @GetMapping("/session")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<String> checkSession() {
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
    public ResponseEntity<Page<User>> getUsers(@RequestParam int page,
                                               @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(userService.findAllUsers(PageRequest.of(page, pageSize)));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/admin/user/{id}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("删除用户成功");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除用户失败");
        }
    }

    @PostMapping("/admin/user")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<String> editUser(@RequestBody UserTypeEditDTO userTypeEditDTO) {
        try {
            userService.editUser(userTypeEditDTO.getUserId(), userTypeEditDTO.getUserType());
            return ResponseEntity.status(HttpStatus.CREATED).body("用户信息修改成功");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("用户信息修改失败");
        }
    }

    @GetMapping("/user/name")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<Boolean> checkDuplicateUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok().body(userService.checkDuplicateUsername(username));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
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