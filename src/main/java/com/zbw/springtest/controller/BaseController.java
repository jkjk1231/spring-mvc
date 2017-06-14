package com.zbw.springtest.controller;

import com.zbw.springtest.model.MediumResult;
import com.zbw.springtest.model.VParams;
import com.zbw.springtest.model.VResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BaseController {

    protected VParams createParams(HttpServletRequest request) {
        String accountIdStr = request.getParameter("account");
        String devId = request.getParameter("devId");
        String os = request.getParameter("os");
        String version = request.getParameter("version");
        String session = request.getParameter("session");
        String params = request.getParameter("params");
        String encrypt = request.getParameter("encrypt");
        String base64 = request.getParameter("base64");

        VParams vParams = new VParams();
        vParams.setDevId(devId);
        if(!StringUtils.isEmpty(accountIdStr)) {
            try {
                vParams.setAccount(Integer.parseInt(accountIdStr));
            } catch (Exception e) {
                vParams.setAccount(-1);
                e.printStackTrace();
            }
        }

        vParams.setOs(os);
        vParams.setVersion(version);
        vParams.setSession(session);
        vParams.setParams(params);
        if(!StringUtils.isEmpty(encrypt)) {
            try {
                vParams.setEncrypt(Boolean.parseBoolean(encrypt));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!StringUtils.isEmpty(base64)) {
            try {
                vParams.setBase64(Boolean.parseBoolean(base64));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        vParams.setIp(request.getRemoteAddr());
        return vParams;
    }

    protected void handleResponse(HttpServletResponse response, VResult result) {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        /** 允许跨域访问 */
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST");
        response.setHeader("Access-Control-Allow-Headers","Access-Control");
        response.setHeader("Allow","POST");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = mapper.writeValueAsString(result);
            response.getWriter().write(jsonResult);
            response.getWriter().flush();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    protected boolean isClientVisited(VParams vParams, VResult msg, HttpServletResponse response) {
        if(StringUtils.isEmpty(vParams.getOs())) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return false;
        }

        if(!("android".equals(vParams.getOs().toLowerCase()) ||
                "ios".equals(vParams.getOs().toLowerCase()))) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return false;
        }

        return true;
    }

    protected VResult handleInfoResult(MediumResult result, VResult msg, boolean hasInfo) {
        if(result.isFlag()) {
            msg.setStatus(VResult.SUCCESS);
            if(!hasInfo) {
                return msg;
            }
            if(result.getInfo() != null) {
                msg.setInfo(result.getInfo());
            } else {
                msg.setDesc(result.getDesc());
            }

        } else {
            msg.setStatus(VResult.FAIL_BUSINESS);
            msg.setDesc(result.getDesc());
        }
        return msg;
    }

    protected boolean isUserLogined(VParams params, VResult msg, HttpServletResponse response) {
        if(StringUtils.isEmpty(params.getAccount())) {
            msg.setStatus(VResult.FAIL_LOGIN_TIME_OUT);
            handleResponse(response, msg);
            return false;
        }

        if(hasUser(params.getAccount())) {
            return true;
        }

        msg.setStatus(VResult.FAIL_LOGIN_TIME_OUT);
        handleResponse(response, msg);
        return false;
    }

    protected boolean hasUser(int userId) {
        return false;
    }
}
