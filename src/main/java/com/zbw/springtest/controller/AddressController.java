package com.zbw.springtest.controller;

import com.zbw.springtest.model.AddressDetail;
import com.zbw.springtest.model.MediumResult;
import com.zbw.springtest.model.VParams;
import com.zbw.springtest.model.VResult;
import com.zbw.springtest.service.IAddress;
import com.zbw.springtest.service.IUser;
import com.zbw.springtest.vo.AddressListObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class AddressController extends BaseController {

    @Resource
    IUser userService;

    @Resource
    IAddress addressService;

    /**
     * 需要参数userId
     * @param request
     * @param response
     */
    @RequestMapping(value="api/getAddressList", method = RequestMethod.GET)
    public void doGetAddressList(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doGetAddressList start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("getAddressList");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(isUserLogined(vParams, msg, response)) {
            return;
        }

        MediumResult<AddressListObject> addressListResult = addressService.doGetAddressList(vParams.getAccount());
        msg = handleInfoResult(addressListResult, msg, true);
        handleResponse(response, msg);
    }

    /**
     * 需要参数userId, addressId.先查询当前用户默认地址(也可让客户端传递)
     * @param request
     * @param response
     */
    @RequestMapping(value="api/setDefaultAddress", method = RequestMethod.POST)
    public void doSetDefaultAddress(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doSetDefaultAddress start");
        // 返回初始化消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("setDefaultAddress");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(!isUserLogined(vParams, msg, response)) {
            return;
        }

        String addressIdStr = request.getParameter("addressId");
        if(StringUtils.isEmpty(addressIdStr)) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return;
        }
        try {
            int addressId = Integer.parseInt(addressIdStr);
            int userId = vParams.getAccount();
            if(addressId >= 0) {
                MediumResult result = addressService.doSetDefaultAddress(userId, addressId);
                if(result.isFlag()) {
                    msg.setStatus(VResult.SUCCESS);
                } else {
                    msg.setStatus(VResult.FAIL_BUSINESS);
                    msg.setDesc(result.getDesc());
                }
                handleResponse(response, msg);
            } else {
                msg.setStatus(VResult.FAIL_PARAM_ERROR);
                handleResponse(response, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
        }
    }

    /**
     * 需要userId, addressDetailId.
     * @param request
     * @param response
     */
    @RequestMapping(value="api/getAddressDetail", method = RequestMethod.POST)
    public void doGetAddressDetail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doGetAddressDetail start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("getAddressDetail");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(!isUserLogined(vParams, msg, response)) {
            return;
        }

        String addressDetailIdStr = request.getParameter("addressDetailId");
        if(StringUtils.isEmpty(addressDetailIdStr)) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return;
        }
        try {
            int addressDetailId = Integer.parseInt(addressDetailIdStr);
            if(addressDetailId >= 0) {
                MediumResult<AddressDetail> result = addressService.doGetAddressDetail(addressDetailId);
                msg = handleInfoResult(result, msg, true);
                handleResponse(response, msg);
            } else {
                msg.setStatus(VResult.FAIL_PARAM_ERROR);
                handleResponse(response, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
        }
    }

    /**
     * 需要参数userId, userName, mobile, area, street, detailAddress, defaultAddress(插两张表)
     * @param request
     * @param response
     */
    @RequestMapping(value="api/createNewAddress", method = RequestMethod.POST)
    public void doCreateNewAddress(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doCreateNewAddress start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("createNewAddress");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(!isUserLogined(vParams, msg, response)) {
            return;
        }

        String userName = request.getParameter("userName");
        String mobile = request.getParameter("mobile");
        String area = request.getParameter("area");
        String street = request.getParameter("street");
        String detailAddress = request.getParameter("detailAddress");
        String defaultAddressStr = request.getParameter("defaultAddress");

        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(area) || StringUtils.isEmpty(street) ||
                StringUtils.isEmpty(detailAddress) || StringUtils.isEmpty(defaultAddressStr)) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return;
        }

        AddressDetail detail = new AddressDetail();
        detail.setUserName(userName);
        detail.setMobile(mobile);
        detail.setArea(area);
        detail.setStreet(street);
        detail.setDetailAddress(detailAddress);

        int userId = vParams.getAccount();

        try {
            boolean defaultAddress = Boolean.parseBoolean(defaultAddressStr);
            MediumResult result = addressService.doCreateNewAddress(detail, defaultAddress, userId);
            if(result.isFlag()) {
                msg.setStatus(VResult.SUCCESS);
            } else {
                msg.setStatus(VResult.FAIL_BUSINESS);
                msg.setDesc(result.getDesc());
            }
            handleResponse(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
        }
    }

    /**
     * 需要参数userId, userName, mobile, area, street, detailAddress, defaultAddress, addressId, addressDetailId（两张表）
     * @param request
     * @param response
     */
    @RequestMapping(value="api/updateAddressDetail", method = RequestMethod.POST)
    public void doUpdateAddressDetail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doUpdateAddressDetail start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("updateAddressDetail");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(!isUserLogined(vParams, msg, response)) {
            return;
        }

        String userName = request.getParameter("userName");
        String mobile = request.getParameter("mobile");
        String area = request.getParameter("area");
        String street = request.getParameter("street");
        String detailAddress = request.getParameter("detailAddress");
        String defaultAddressStr = request.getParameter("defaultAddress");
        String addressIdStr = request.getParameter("addressId");
        String addressDetailIdStr = request.getParameter("addressDetailId");

        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(area) || StringUtils.isEmpty(street) ||
                StringUtils.isEmpty(detailAddress) || StringUtils.isEmpty(defaultAddressStr) ||
                StringUtils.isEmpty(addressIdStr) || StringUtils.isEmpty(addressDetailIdStr)) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return;
        }

        AddressDetail detail = new AddressDetail();
        detail.setUserName(userName);
        detail.setMobile(mobile);
        detail.setArea(area);
        detail.setStreet(street);
        detail.setDetailAddress(detailAddress);

        int userId = vParams.getAccount();

        try {
            boolean defaultAddress = Boolean.parseBoolean(defaultAddressStr);
            int addressId = Integer.parseInt(addressIdStr);
            int addressDetailId = Integer.parseInt(addressDetailIdStr);
            if(addressDetailId < 0 || addressId < 0) {
                msg.setStatus(VResult.FAIL_PARAM_ERROR);
                handleResponse(response, msg);
                return;
            }
            detail.setId(addressDetailId);
            MediumResult result = addressService.doUpdateAddressDetail(detail, defaultAddress, addressId);
            msg = handleInfoResult(result, msg, false);
            handleResponse(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
        }
    }

    @RequestMapping(value="api/deleteAddress", method = RequestMethod.POST)
    public void doDeleteAddress(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("doUpdateAddressDetail start");
        // 初始化返回消息
        VResult msg = new VResult();
        // 获取参数
        VParams vParams = createParams(request);
        vParams.setApi("deleteAddress");

        if(!isClientVisited(vParams, msg, response)) {
            return;
        }

        if(!isUserLogined(vParams, msg, response)) {
            return;
        }

        String addressIdStr = request.getParameter("addressId");
        if(StringUtils.isEmpty(addressIdStr)) {
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
            return;
        }

        try {
            int addressId = Integer.parseInt(addressIdStr);
            if(addressId < 0) {
                msg.setStatus(VResult.FAIL_PARAM_ERROR);
                handleResponse(response, msg);
                return;
            }

            MediumResult result = addressService.doDeleteAddress(addressId);
            msg = handleInfoResult(result, msg, false);
            handleResponse(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(VResult.FAIL_PARAM_ERROR);
            handleResponse(response, msg);
        }
    }

    @Override
    protected boolean hasUser(int userId) {
        return userService.hasUser(userId);
    }
}
