package com.imooc.dao.impl;

import com.imooc.dao.ProductDao;
import com.imooc.domain.Product;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findAll() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List <Product> list=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="SELECT * from product p LEFT JOIN category c on p.cid=c.cid order by p.pid desc";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            list=new ArrayList<>();
            while (resultSet.next()){
                Product product=new Product();
                product.setPid(resultSet.getInt("pid"));
                product.setPname(resultSet.getString("pname"));
                product.setAuthor(resultSet.getString("author"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setFilename(resultSet.getString("filename"));
                product.setPath(resultSet.getString("path"));
                product.getCategory().setCid(resultSet.getInt("cid"));
                product.getCategory().setCname(resultSet.getString("cname"));
                product.getCategory().setCdesc(resultSet.getString("cdesc"));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public void save(Product product) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="insert into product values(null,?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getPname());
            preparedStatement.setString(2,product.getAuthor());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getFilename());
            preparedStatement.setString(6,product.getPath());
            preparedStatement.setInt(7,product.getCategory().getCid());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public Product findOne(Integer pid) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="SELECT * from product p LEFT JOIN category c on p.cid=c.cid where p.pid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Product product=new Product();
                product.setPid(resultSet.getInt("pid"));
                product.setPname(resultSet.getString("pname"));
                product.setAuthor(resultSet.getString("author"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setFilename(resultSet.getString("filename"));
                product.setPath(resultSet.getString("path"));
                product.getCategory().setCid(resultSet.getInt("cid"));
                product.getCategory().setCname(resultSet.getString("cname"));
                product.getCategory().setCname(resultSet.getString("cdesc"));
            return product;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(Product product) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="update product set pname=?,author=?,price=?,description=?,filename=?,path=?,cid=? where pid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getPname());
            preparedStatement.setString(2,product.getAuthor());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getFilename());
            preparedStatement.setString(6,product.getPath());
            preparedStatement.setObject(7,product.getCategory().getCid());
            preparedStatement.setInt(8,product.getPid());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public void delete(Integer pid) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="delete from product where pid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(preparedStatement,connection);
        }
    }

    @Override
    public List<Product> findByCid(Integer cid) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List <Product> list=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="select * from product p left join category c on p.cid=c.cid where p.cid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,cid);
            resultSet=preparedStatement.executeQuery();
            list=new ArrayList<>();
            while (resultSet.next()){
                Product product=new Product();
                product.setPid(resultSet.getInt("pid"));
                product.setPname(resultSet.getString("pname"));
                product.setAuthor(resultSet.getString("author"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setFilename(resultSet.getString("filename"));
                product.setPath(resultSet.getString("path"));
                product.getCategory().setCid(resultSet.getInt("cid"));
                product.getCategory().setCname(resultSet.getString("cname"));
                product.getCategory().setCname(resultSet.getString("cdesc"));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public void update(Connection connection, Product product) {
        PreparedStatement preparedStatement=null;
        try {
            String sql="update product set pname=?,author=?,price=?,description=?,filename=?,path=?,cid=? where pid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getPname());
            preparedStatement.setString(2,product.getAuthor());
            preparedStatement.setDouble(3,product.getPrice());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getFilename());
            preparedStatement.setString(6,product.getPath());
            preparedStatement.setObject(7,product.getCategory().getCid());
            preparedStatement.setInt(8,product.getPid());
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

    @Override
    public int findCount() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Long count=0l;
        try{
            connection=JDBCUtils.getConnection();
            String sql="select count(*) as count from product";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                count=resultSet.getLong("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return count.intValue();
    }

    @Override
    public List<Product> findByPage(int begin, int limit) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Product> list=null;
        try {
            connection=JDBCUtils.getConnection();
            String sql="select * from product limit ?,?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,begin);
            preparedStatement.setInt(2,limit);
            resultSet=preparedStatement.executeQuery();
            list=new ArrayList<>();
            while (resultSet.next()){
                Product product=new Product();
                product.setPid(resultSet.getInt("pid"));
                product.setPname(resultSet.getString("pname"));
                product.setAuthor(resultSet.getString("author"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setFilename(resultSet.getString("filename"));
                product.setPath(resultSet.getString("path"));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
