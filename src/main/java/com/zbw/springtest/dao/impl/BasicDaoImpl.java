package com.zbw.springtest.dao.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public abstract class BasicDaoImpl {
    protected SqlSessionFactory sessionFactory;
    protected SqlSession session;

    public BasicDaoImpl() {
        String resource = "config/mybatis-config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            registerDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void registerDao();
}
