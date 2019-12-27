package com.imooc.dao;

import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class UserDaoTest {
    @Test
    public void demo(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            User user=new User();
            user.setUsername("imooc");
            user.setPassword("123456");
            connection= JDBCUtils.getConnection();
            String sql="select * from user where username=? and password=?";
            //预编译sql
            preparedStatement=connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                User existUser=new User();
                existUser.setUid(resultSet.getInt("uid"));
                existUser.setUsername(resultSet.getString("username"));
                existUser.setPassword(resultSet.getString("password"));
                System.out.println(existUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
    }

}
