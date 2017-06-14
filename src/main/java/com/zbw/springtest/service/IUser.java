package com.zbw.springtest.service;

import com.zbw.springtest.model.User;
import org.springframework.web.servlet.ModelAndView;


public interface IUser {

    public ModelAndView doLogin(String username, String password, String loginurl);

    public ModelAndView doRegister(String username, String password, String loginurl);

    public User doLoginClient(String userName, String password);

    public boolean hasUser(int userId);
}
