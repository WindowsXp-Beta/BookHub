package com.windowsxp.bookstore.utils.sessionutils;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.enumerate.UserType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SessionUtil {

    public enum AuthType {
        PASS, USER, ADMIN
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Auth {
        AuthType authType() default AuthType.PASS;
    }

    public static boolean checkAuth(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if(session != null) {
                UserType userType = (UserType) session.getAttribute(Constant.USER_TYPE);
                return userType != UserType.BANNED;
            }
        }
        return false;
    }

    public static boolean checkAdmin(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession(false);
        UserType userType = (UserType) session.getAttribute(Constant.USER_TYPE);
        return userType == UserType.ADMIN;
    }

    public static JSONObject getAuth(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if(session != null) {
                JSONObject ret = new JSONObject();
                ret.put(Constant.USER_ID, session.getAttribute(Constant.USER_ID));
                ret.put(Constant.USERNAME, session.getAttribute(Constant.USERNAME));
                ret.put(Constant.USER_TYPE, session.getAttribute(Constant.USER_TYPE));
                return ret;
            }
        }
        return null;
    }

    public static void setSession(JSONObject data){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();

            for(Object str:data.keySet()){
                String key = (String)str;
                Object val = data.get(key);
                session.setAttribute(key, val);
            }
        }
    }

    public static Boolean removeSession(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if(session != null) {
                session.invalidate();
            }
        }
        return true;
    }
}