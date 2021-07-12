package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.service.UserService;
import com.windowsxp.bookstore.utils.msgutils.Msg;
import com.windowsxp.bookstore.utils.msgutils.MsgCode;
import com.windowsxp.bookstore.utils.msgutils.MsgUtil;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg login(@RequestBody Map<String, String> params) {
        System.out.println("login");
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        User auth = userService.checkUser(username, password);
        if (auth != null && auth.getUserType() != -1) {
            JSONObject obj = new JSONObject();
            obj.put(Constant.USER_ID, auth.getUserId());
            obj.put(Constant.USERNAME, auth.getUserName());
            obj.put(Constant.USER_TYPE, auth.getUserType());
            SessionUtil.setSession(obj);

            JSONObject data = (JSONObject) JSON.toJSON(auth);
            data.remove(Constant.PASSWORD);

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
        } else if(auth != null && auth.getUserType() == -1) {
            return MsgUtil.makeMsg(MsgCode.BAN_USER_ERROR);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @RequestMapping("/logout")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg logout() {
        System.out.println("logout");
        Boolean status = SessionUtil.removeSession();

        if (status) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }

    @RequestMapping("/checkSession")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg checkSession() {
        System.out.println("checkSession");
        JSONObject auth = SessionUtil.getAuth();

        if (auth == null) {
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        } else {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }

    @RequestMapping("/getUsers")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<User> getUsers() {
        System.out.println("getUsers");
        return userService.findAllUsers();
    }

    @RequestMapping("/deleteUser")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg deleteUser(@RequestParam("id") Integer id) {
        System.out.println("deleteUser: " + id);
        userService.deleteUser(id);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_USER_MSG);
    }

    @RequestMapping("/editUser")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg editUser(@RequestBody Map<String, String> params) {
        System.out.println("editUser");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        User user = userService.getUser(userId);
        System.out.println(user);
        Integer userType = Integer.valueOf(params.get(Constant.USER_TYPE));
        user.setUserType(userType);
        System.out.println(userType);
        userService.addUser(user);
        return MsgUtil.makeMsg(MsgCode.SUCCESS);
    }

    @RequestMapping("/checkUsername")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg checkUsername(@RequestBody Map<String, String> params) {
        System.out.println("check if username repeated");
        String newUsername = params.get("username");
        List<User> userList = userService.findAllUsers();
        for(User user: userList) {
            if(user.getUserName().equals(newUsername)) return MsgUtil.makeMsg(MsgCode.ERROR);
        }
        return MsgUtil.makeMsg(MsgCode.SUCCESS);
    }

    @RequestMapping("/register")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addUser(@RequestBody Map<String, String> params) {
        System.out.println("add User");
        User newUser = new User();
        newUser.setUserName(params.get("username"));
        newUser.setUserType(1);
        newUser.setAddress(params.get("address"));
        newUser.setEmail(params.get("Email"));
        newUser.setPassword(params.get("password"));
        userService.addUser(newUser);
        Integer userId = newUser.getUserId();

        JSONObject obj = new JSONObject();
        obj.put(Constant.USER_ID, userId);
        obj.put(Constant.USERNAME, newUser.getUserName());
        obj.put(Constant.USER_TYPE, newUser.getUserType());
        SessionUtil.setSession(obj);

        JSONObject data = (JSONObject) JSON.toJSON(newUser);
        data.remove(Constant.PASSWORD);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG, data);
    }
}