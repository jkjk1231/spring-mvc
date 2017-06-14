package com.zbw.springtest.service.impl;

import com.zbw.springtest.dao.IAddressDao;
import com.zbw.springtest.dao.IAddressDetailDao;
import com.zbw.springtest.dao.impl.AddressDaoImpl;
import com.zbw.springtest.dao.impl.AddressDetailDaoImpl;
import com.zbw.springtest.model.AddressDetail;
import com.zbw.springtest.model.AddressList;
import com.zbw.springtest.model.MediumResult;
import com.zbw.springtest.service.IAddress;
import com.zbw.springtest.vo.AddressListObject;

import java.util.List;


public class AddressImpl implements IAddress {

    private IAddressDao addressDao;
    private IAddressDetailDao addressDetailDao;

    public AddressImpl() {
        addressDao = new AddressDaoImpl();
        addressDetailDao = new AddressDetailDaoImpl();
    }

    public MediumResult<AddressListObject> doGetAddressList(int userId) {
        List<AddressList> list =  addressDao.findByUserId(userId);
        MediumResult<AddressListObject> result = new MediumResult<AddressListObject>();
        result.setFlag(true);
        if(list == null || list.isEmpty()) {
            result.setDesc("无数据");
        } else {
            AddressListObject addressListObject = new AddressListObject();
            addressListObject.setAdressList(list);
            result.setInfo(addressListObject);
        }
        return result;
    }

    public MediumResult doSetDefaultAddress(int userId, int addressId) {
        MediumResult result = new MediumResult();
        int prevAddressId = addressDao.findByDefaultAddress(userId);

        boolean prevSuccess = addressDao.updateDefaultAddress(prevAddressId, false);
        boolean nowSuccess = addressDao.updateDefaultAddress(addressId, true);
        if(prevSuccess && nowSuccess) {
            result.setFlag(true);
            if(prevAddressId < 0) {
                result.setDesc("之前没有设置过默认地址");
            }
        } else {
            result.setFlag(false);
            result.setDesc("业务失败，请重新设置");
        }
        return result;
    }

    /**
     * 注意：客户端显示是否为默认地址由上一个地址列表页面提供
     * @param addressDetailId
     * @return
     */
    public MediumResult<AddressDetail> doGetAddressDetail(int addressDetailId) {
        AddressDetail detail = addressDetailDao.findById(addressDetailId);
        MediumResult<AddressDetail> result = new MediumResult<AddressDetail>();
        result.setFlag(true);
        result.setInfo(detail);
        return result;
    }

    public MediumResult doCreateNewAddress(AddressDetail detail, boolean isDefaultAddress, int userId) {
        addressDetailDao.save(detail);
        int addressDetailId = addressDetailDao.findNewestInsertId();

        AddressList address = new AddressList();
        address.setDefaultAddress(isDefaultAddress);
        address.setDetailAddress(detail.getDetailAddress());
        address.setDetailAddressId(addressDetailId);
        address.setMobile(detail.getMobile());
        address.setUserId(userId);
        address.setUserName(detail.getUserName());
        addressDao.save(address);

        int addressId = addressDao.findNewestInsertId();

        MediumResult result = new MediumResult();
        result.setFlag(true);
        return result;
    }

    public MediumResult doUpdateAddressDetail(AddressDetail detail, boolean isDefaultAddress, int addressId) {
        addressDetailDao.update(detail);
        int addressDetailId = detail.getId();
        AddressList address = new AddressList();
        address.setUserName(detail.getUserName());
        address.setMobile(detail.getMobile());
        address.setDetailAddress(detail.getDetailAddress());
        address.setDefaultAddress(isDefaultAddress);
        address.setId(addressId);

        addressDao.update(address);

        MediumResult result = new MediumResult();
        result.setFlag(true);
        return result;
    }

    public MediumResult doDeleteAddress(int addressId) {
        int detailId = addressDao.findDetailIdById(addressId);
        addressDetailDao.delete(detailId);

        addressDao.delete(addressId);

        MediumResult result = new MediumResult();
        result.setFlag(true);
        return result;
    }
}
