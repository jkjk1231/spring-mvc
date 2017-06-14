package com.zbw.springtest.controller;

import com.zbw.springtest.model.User;
import com.zbw.springtest.model.VParams;
import com.zbw.springtest.model.VResult;
import com.zbw.springtest.service.IUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends BaseController {

    @Resource
    IUser userService;

    @Resource
    HttpServletRequest request;

    @RequestMapping("register")
    public ModelAndView toRegister() {
        System.out.println("----UserController toRegister---");
        return new ModelAndView("register.jsp");
    }

    @RequestMapping("register2")
    public ModelAndView doRegister() {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("----UserController register receive password == " + password);
        return userService.doRegister(userName, password, "register.jsp");
    }

    @RequestMapping("login")
    public ModelAndView toLogin() {
        System.out.println("----UserController toLogin---");
        return new ModelAndView("login.jsp");
    }

    @RequestMapping("login3")
    public ModelAndView doLogin() {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("----UserController doLogin receive password == " + password);
        return userService.doLogin(userName, password, "login.jsp");
    }

    @RequestMapping(value="api/login2", method = RequestMethod.POST)
    public @ResponseBody VResult<User> doLoginClient(HttpServletRequest request, HttpServletResponse response) {
    	VResult msg = new VResult();
//    public void doLoginClient(HttpServletRequest request, HttpServletResponse response) {
        // 记录开始时间
   /*     long start = System.currentTimeMillis();
        System.out.println("dologinClient start");
        // 初始化返回消息
        
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("login");
        vParams.setIp(request.getRemoteAddr());
//        String result = "非客户端方法";
        if(StringUtils.isEmpty(vParams.getOs())) {
//            handleResponse(response, result);
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            return msg;
        }

        if(!("android".equals(vParams.getOs().toLowerCase()) ||
                "ios".equals(vParams.getOs().toLowerCase()))) {
//            handleResponse(response, result);
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            return msg;
        }*/

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("1");
        User user = userService.doLoginClient(userName, password);
        if(-1 == user.getId()) {
            msg.setStatus(VResult.SUCCESS_NO_RESULT);
            msg.setDesc(user.getPassword());
        } else {
            msg.setStatus(VResult.SUCCESS);
            msg.setInfo(user);
        }
//        handleResponse(response, result);
        return msg;
    }

    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public void doLoginClient2(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("dologinClient start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("login");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.doLoginClient(userName, password);
        if(-1 == user.getId()) {
            msg.setStatus(VResult.SUCCESS_NO_RESULT);
            msg.setDesc(user.getPassword());
        } else {
            msg.setStatus(VResult.SUCCESS);
            msg.setInfo(user);
        }
        handleResponse(response, msg);
    }
    
    @RequestMapping(value="test", method = RequestMethod.POST)
    public ModelAndView test(HttpServletRequest request){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userName);
        System.out.println(password);
        if(userName.equals("admin")&&password.equals("123"))
        return new ModelAndView("success.jsp");
        else return new ModelAndView("login.jsp");
    }
}
