package com.imooc.service.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.CategoryDaoImpl;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.Category;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() {
        CategoryDao categoryDao=new CategoryDaoImpl();
        return categoryDao.findAll();
    }

    @Override
    public void save(Category category) {
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.save(category);
    }

    @Override
    public Category findOne(Integer cid) {
        CategoryDao categoryDao=new CategoryDaoImpl();
        return categoryDao.findOne(cid);
    }

    @Override
    public void update(Category category) {
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.update(category);
    }

    @Override
    public void delete(Integer cid) {
        /**
         * 事务管理：在业务层统一创建连接对象，保证多个DAO使用同一个连接
         * 1.创建连接之后，将连接对象传递给DAO
         * 2.创建一个连接对象，将连接绑定当前线程中（ThreadLocal）
         */
        Connection connection=null;
        try{
            connection= JDBCUtils.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            CategoryDao categoryDao=new CategoryDaoImpl();
            ProductDao productDao=new ProductDaoImpl();
            List<Product> productList=productDao.findByCid(cid);
            for (Product product:productList) {
                product.getCategory().setCid(null);
                productDao.update(connection,product);
            }
            //手动造异常测试下事务回滚
            //int d=1/0;
            categoryDao.delete(connection,cid);
            //提交事务
            connection.commit();
        }catch (Exception e){
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
