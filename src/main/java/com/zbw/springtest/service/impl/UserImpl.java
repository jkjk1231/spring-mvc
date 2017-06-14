package com.zbw.springtest.service.impl;

import com.zbw.springtest.Constants;
import com.zbw.springtest.dao.IUserDao;
import com.zbw.springtest.dao.impl.UserDaoImpl;
import com.zbw.springtest.model.User;
import com.zbw.springtest.service.IUser;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;


public class UserImpl implements IUser {

//    @Resource
//    IUserDao userDao;

    private IUserDao userDao;

    public UserImpl() {
        userDao = new UserDaoImpl();
    }

    public ModelAndView doLogin(String username, String password, String loginurl) {
        String resultUrl = null, modelName = null, modelObject = null;
        switch (realLogin(username, password)) {
            case Constants.LoginStatus.SUCCESS:
                resultUrl = "success.jsp";
                modelName = "username";
                modelObject = username;
                break;
            case Constants.LoginStatus.EMPTY_NAME:
                resultUrl = loginurl;
                modelName = "error";
                modelObject = "用户名为空";
                break;
            case Constants.LoginStatus.EMPTY_PWD:
                resultUrl = loginurl;
                modelName = "error";
                modelObject = "密码为空";
                break;
            case Constants.LoginStatus.NO_USER:
                resultUrl = loginurl;
                modelName = "error";
                modelObject = "用户不存在，请先注册！";
                break;
            case Constants.LoginStatus.ERRPR_PWD:
                resultUrl = loginurl;
                modelName = "error";
                modelObject = "密码不正确";
                break;
        }

        if(StringUtils.isEmpty(resultUrl)) {
            return null;
        } else {
            return new ModelAndView(resultUrl,modelName,modelObject);
        }
    }

    public ModelAndView doRegister(String username, String password, String loginurl) {
        if(StringUtils.isEmpty(username)) {
            return new ModelAndView(loginurl, "error", "用户名为空");
        }

        if(StringUtils.isEmpty(password)) {
            return new ModelAndView(loginurl, "error", "密码为空");
        }

        // 查询用户名
        User user = userDao.findByUserName(username);
        if(user == null) {
            userDao.save(new User(username, password));
            return new ModelAndView(loginurl, "success", "用户注册成功！");
        } else {
            // 查询到重复用户名
            return new ModelAndView(loginurl, "error", "用户名重复");
        }
    }

    /**
     * 客户端请求
     * @param userName
     * @param password
     * 这里当查询失败时借用password的值来表示错误原因
     */
    public User doLoginClient(String userName, String password) {
        if(StringUtils.isEmpty(userName)) {
            return new User(-1, "用户名为空", null);
        }

        if(StringUtils.isEmpty(password)) {
            return new User(-1, "密码为空", null);
        }

        User user = userDao.findByUserName(userName);
        if(user == null) {
            // 用户名不正确
            return new User(-1, "用户名不正确，未查询到该用户", null);
        } else {
            if(password.equals(user.getPassword())) {
                return user;
            } else {
                // 用户密码错误
                return new User(-1, "用户密码错误", null);
            }
        }
    }

    public boolean hasUser(int userId) {
        User user = userDao.findById(userId);
        if(user == null) {
            return false;
        }
        return true;
    }

    /**
    *
    * @param userName
    * @param password
    * @return 0: 成功，1: 用户名为空, 2: 密码为空, 3: 用户不存在，请先注册！, 4: 密码不正确
    */
    private int realLogin(String userName, String password) {
        if(StringUtils.isEmpty(userName)) {
            return Constants.LoginStatus.EMPTY_NAME;
        }

        if(StringUtils.isEmpty(password)) {
            return Constants.LoginStatus.EMPTY_PWD;
        }

        User user = userDao.findByUserName(userName);
        if(user == null) {
            // 未查询到该用户
            return Constants.LoginStatus.NO_USER;
        } else {
            if(password.equals(user.getPassword())) {
                return Constants.LoginStatus.SUCCESS;
            } else {
                // 用户密码错误
                return Constants.LoginStatus.ERRPR_PWD;
            }
        }
    }
}
