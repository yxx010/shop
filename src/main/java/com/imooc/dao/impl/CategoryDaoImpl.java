package com.imooc.dao.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.domain.Category;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        System.out.println("dao find all执行了");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Category> list=null;
        try{
            //1.获取连接
            connection= JDBCUtils.getConnection();
            //2.编写sql
            String sql="select * from category";
            //3.预编译sql
            preparedStatement=connection.prepareStatement(sql);
            //4.设置参数
            //5.执行sql
            resultSet=preparedStatement.executeQuery();
            //6.结果处理
            list=new ArrayList<>();
            while (resultSet.next()){
                Category category=new Category();
                category.setCid(resultSet.getInt("cid"));
                category.setCname(resultSet.getString("cname"));
                category.setCdesc(resultSet.getString("cdesc"));
                list.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //7.释放资源
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
