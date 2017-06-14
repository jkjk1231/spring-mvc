package com.zbw.springtest.dao;

import com.zbw.springtest.model.AddressDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface IAddressDetailDao {

    @Insert("insert into t_address_detail(user_name,mobile,area,street,detail_address) values(#{userName},#{mobile},#{area},#{street},#{detailAddress})")
    void save(AddressDetail detail);

    @Select("select max(id) from t_address_detail")
    int findNewestInsertId();

    @Update("update t_address_detail set user_name=#{userName},mobile=#{mobile},area=#{area},street=#{street},detail_address=#{detailAddress} where id=#{id}")
    boolean update(AddressDetail detail);

    @Delete("delete from t_address_detail where id=#{id}")
    boolean delete(int id);

    @Select("select id,user_name userName,mobile,area,street,detail_address detailAddress from t_address_detail where id=#{id}")
    AddressDetail findById(int id);
}
