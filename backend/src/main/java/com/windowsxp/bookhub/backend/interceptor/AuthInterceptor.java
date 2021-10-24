package com.windowsxp.bookhub.backend.interceptor;

import com.windowsxp.bookhub.backend.utils.sessionutils.SessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
        if(!(object instanceof HandlerMethod)) {
            // return true for OPTIONS requests
            return true;
        }
        SessionUtil.Auth auth = ((HandlerMethod) object).getMethod().getAnnotation(SessionUtil.Auth.class);

        if(auth == null) {
            sendErrorBack(response,"controller未标注解");
            return false;
        }

        SessionUtil.AuthType authType = auth.authType();

        switch (authType) {
            case USER:
                if(!SessionUtil.checkAuth()){
                    sendErrorBack(response, "登录失效");
                    return false;
                }
                else return true;
            case ADMIN:
                if(!SessionUtil.checkAuth()){
                    sendErrorBack(response, "登录失效");
                    return false;
                }
                else if(!SessionUtil.checkAdmin()){
                    sendErrorBack(response, "需要管理员权限");
                    return false;
                }
                else return true;
            default:
                return true;
        }
    }

    private void sendErrorBack(HttpServletResponse response, String errorMessage){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(errorMessage);
        } catch (IOException e) {
            System.out.println("send json back error");
        }
    }
}
