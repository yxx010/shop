package com.imooc.dao.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.domain.Category;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
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

    @Override
    public void save(Category category) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="insert into category values(null,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,category.getCname());
            preparedStatement.setString(2,category.getCdesc());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public Category findOne(Integer cid) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="select * from category where cid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,cid);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Category category=new Category();
                category.setCid(resultSet.getInt("cid"));
                category.setCname(resultSet.getString("cname"));
                category.setCdesc(resultSet.getString("cdesc"));
                return category;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return null;
    }

    @Override
    public void update(Category category) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            connection=JDBCUtils.getConnection();
            String sql="update category set cname=?,cdesc=? where cid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,category.getCname());
            preparedStatement.setString(2,category.getCdesc());
            preparedStatement.setInt(3,category.getCid());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public void delete(Integer cid) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            connection=JDBCUtils.getConnection();
            String sql="delete from category where cid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,cid);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public void delete(Connection connection, Integer cid) {
        PreparedStatement preparedStatement=null;
        try{
            String sql="delete from category where cid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,cid);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
