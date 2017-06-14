package com.zbw.springtest.service;

import com.zbw.springtest.model.AddressDetail;
import com.zbw.springtest.model.MediumResult;
import com.zbw.springtest.vo.AddressListObject;


public interface IAddress {

    public MediumResult<AddressListObject> doGetAddressList(int userId);

    public MediumResult doSetDefaultAddress(int userId, int addressId);

    public MediumResult<AddressDetail> doGetAddressDetail(int addressDetailId);

    public MediumResult doCreateNewAddress(AddressDetail detail, boolean isDefaultAddress, int userId);

    public MediumResult doUpdateAddressDetail(AddressDetail detail, boolean isDefaultAddress, int addressId);

    public MediumResult doDeleteAddress(int addressId);
}
