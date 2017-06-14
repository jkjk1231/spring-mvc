package com.zbw.springtest.dao;

import com.zbw.springtest.model.AddressList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface IAddressDao {

    @Insert("insert into t_address(user_name,mobile,detail_address,default_address,detail_address_id,user_id) values(#{userName},#{mobile},#{detailAddress},#{defaultAddress},#{detailAddressId},#{userId})")
    void save(AddressList address);

    @Select("select max(id) from t_address")
    int findNewestInsertId();

    @Update("update t_address set user_name=#{userName},mobile=#{mobile},detail_address=#{detailAddress},default_address=#{defaultAddress} where id=#{id}")
    boolean update(AddressList address);

    @Update("update t_address set default_address=#{defaultAddress} where id=#{addressId}")
    boolean updateDefaultAddress(int addressId, boolean defaultAddress);

    @Delete("delete from t_address where id=#{id}")
    boolean delete(int id);

    @Select("select id,user_name userName,mobile,detail_address detailAddress,default_address defaultAddress,detail_address_id detailAddressId,user_id userId from t_address where id=#{id}")
    AddressList findById(int id);

    @Select("select id from t_address where default_address=true and user_id=#{userId}")
    int findByDefaultAddress(int userId);

    @Select("select id,user_name userName,mobile,detail_address detailAddress,default_address defaultAddress,detail_address_id detailAddressId,user_id userId from t_address where id=#{userId}")
    List<AddressList> findByUserId(int userId);

    @Select("select detail_address_id from t_address where id=#{id}")
    int findDetailIdById(int id);


}
