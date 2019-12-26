package com.imooc.dao.impl;

import com.imooc.dao.UserDao;
import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {


    @Override
    public User login(User user) {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            //连接
            connection=JDBCUtils.getConnection();
            //编写sql
            String sql="select * from user where username=? and password=?";
            //预编译sql
            preparedStatement=connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            //执行
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                User existUser=new User();
                existUser.setUid(resultSet.getInt("uid"));
                existUser.setUsername(resultSet.getString("username"));
                existUser.setPassword(resultSet.getString("password"));
                return existUser;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return null;
    }
}
