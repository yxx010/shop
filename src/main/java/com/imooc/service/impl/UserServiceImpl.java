package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.dao.impl.UserDaoImpl;
import com.imooc.domain.User;
import com.imooc.service.UserService;

public class UserServiceImpl implements UserService {

    public User login(User user) {
        UserDao userDao=new UserDaoImpl();
        userDao.login(user);
        return null;
    }
}
