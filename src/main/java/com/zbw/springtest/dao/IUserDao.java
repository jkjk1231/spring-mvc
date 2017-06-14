package com.zbw.springtest.dao;

import com.zbw.springtest.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface IUserDao {

    @Insert("insert into t_user(user_name,user_password) values(#{userName},#{password})")
    void save(User user);

    @Update("update t_user set user_name=#{userName},user_password=#{password} where user_id=#{id}")
    boolean update(User user);

    @Delete("delete from t_user where user_id=#{id}")
    boolean delete(int id);

    @Select("select user_id id,user_name userName,user_password password from t_user where user_id=#{id}")
    User findById(int id);

    @Select("select user_id id,user_name userName,user_password password from t_user where user_name=#{userName}")
    User findByUserName(String userName);

    @Select("select user_id id,user_name userName,user_password password from t_user")
    List<User> findAll();
}
