package com.zbw.springtest.service.impl;

import com.zbw.springtest.service.ILogin;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;


public class LoginImpl implements ILogin {

    public ModelAndView doLogin(String username, String password, String loginurl) {
        if(StringUtils.isEmpty(username)) {
            return new ModelAndView(loginurl, "error", "用户名为空");
        }

        if(StringUtils.isEmpty(password)) {
            return new ModelAndView(loginurl, "error", "密码为空");
        }

        if(password.equals("123456") && username.equals("admin")){
            return new ModelAndView("success.jsp","username",username);
        }
        return new ModelAndView(loginurl,"error","用户名和密码不正确");
    }
}
