package com.zbw.springtest.service;

import org.springframework.web.servlet.ModelAndView;


public interface ILogin {

    public ModelAndView doLogin(String username, String password, String loginurl);
}
