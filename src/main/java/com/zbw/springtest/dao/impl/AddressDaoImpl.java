package com.zbw.springtest.dao.impl;

import com.zbw.springtest.dao.IAddressDao;
import com.zbw.springtest.model.AddressList;

import java.util.List;

public class AddressDaoImpl extends BasicDaoImpl implements IAddressDao {

    private IAddressDao addressDao;

    public void save(AddressList address) {
        addressDao.save(address);
        session.commit();  //一定要记得commit
    }

    public int findNewestInsertId() {
        return addressDao.findNewestInsertId();
    }

    public boolean update(AddressList address) {
        addressDao.update(address);
        session.commit();  //一定要记得commit
        return true;
    }

    public boolean updateDefaultAddress(int addressId, boolean defaultAddress) {
        addressDao.updateDefaultAddress(addressId, defaultAddress);
        session.commit();
        return true;
    }

    public boolean delete(int id) {
        addressDao.delete(id);
        session.commit();  //一定要记得commit
        return true;
    }

    public AddressList findById(int id) {
        return addressDao.findById(id);
    }

    public int findByDefaultAddress(int userId) {
        return addressDao.findByDefaultAddress(userId);
    }

    public List<AddressList> findByUserId(int userId) {
        return addressDao.findByUserId(userId);
    }

    public int findDetailIdById(int id) {
        return addressDao.findDetailIdById(id);
    }

    @Override
    protected void registerDao() {
        addressDao = session.getMapper(IAddressDao.class);
    }
}
