package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.service.UserService;
import com.windowsxp.bookstore.utils.msgutils.Msg;
import com.windowsxp.bookstore.utils.msgutils.MsgCode;
import com.windowsxp.bookstore.utils.msgutils.MsgUtil;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (auth != null) {
            JSONObject obj = new JSONObject();
            obj.put(Constant.USER_ID, auth.getUserId());
            obj.put(Constant.USERNAME, auth.getUserName());
            obj.put(Constant.USER_TYPE, auth.getUserType());
            SessionUtil.setSession(obj);

            JSONObject data = (JSONObject) JSON.toJSON(auth);
            data.remove(Constant.PASSWORD);

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
        } else {
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
}