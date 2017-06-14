package com.zbw.springtest.dao.impl;

import com.zbw.springtest.dao.IAddressDetailDao;
import com.zbw.springtest.model.AddressDetail;


public class AddressDetailDaoImpl extends BasicDaoImpl implements IAddressDetailDao {

    private IAddressDetailDao addressDao;

    public void save(AddressDetail address) {
        addressDao.save(address);
        session.commit();  //一定要记得commit
    }

    public int findNewestInsertId() {
        return addressDao.findNewestInsertId();
    }

    public boolean update(AddressDetail address) {
        addressDao.update(address);
        session.commit();  //一定要记得commit
        return true;
    }

    public boolean delete(int id) {
        addressDao.delete(id);
        session.commit();  //一定要记得commit
        return true;
    }

    public AddressDetail findById(int id) {
        return addressDao.findById(id);
    }

    @Override
    protected void registerDao() {
        addressDao = session.getMapper(IAddressDetailDao.class);
    }
}
