package com.zbw.springtest.dao.impl;

import com.zbw.springtest.dao.IUserDao;
import com.zbw.springtest.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private IUserDao userDao;

    public UserDaoImpl() {
        String resource = "config/mybatis-config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            userDao = session.getMapper(IUserDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
//        String statement = "userMapper.save";
//        session.insert(statement, user);
        userDao.save(user);
        session.commit();  //一定要记得commit
    }

    public boolean update(User user) {
//        String statement = "userMapper.update";
//        session.update(statement, user);
        userDao.update(user);
        session.commit();  //一定要记得commit
        return true;
    }

    public boolean delete(int id) {
//        String statement = "userMapper.delete";
//        session.delete(statement, id);
        userDao.delete(id);
        session.commit();  //一定要记得commit
        return true;
    }

    public User findById(int id) {
//        String statement = "userMapper.findById";
//        User user = (User)session.selectOne(statement, id);
        return userDao.findById(id);
    }

    public User findByUserName(String userName) {
//        String statement = "userMapper.findByUserName";
//        User user = (User) session.selectList(statement, userName);
        return userDao.findByUserName(userName);
    }

    public List<User> findAll() {
//        String statement = "userMapper.findAll";
//        List<User> userList = (List)session.selectList(statement);
        return userDao.findAll();
    }
}
