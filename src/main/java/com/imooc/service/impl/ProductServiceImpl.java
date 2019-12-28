package com.imooc.service.impl;

import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.Product;
import com.imooc.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAll() {
        ProductDao productDao=new ProductDaoImpl();
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        ProductDao productDao=new ProductDaoImpl();
        productDao.save(product);
    }

    @Override
    public Product findOne(Integer pid) {
        ProductDao productDao=new ProductDaoImpl();
        return productDao.findOne(pid);
    }

    @Override
    public void update(Product product) {
        ProductDao productDao=new ProductDaoImpl();
        productDao.update(product);
    }

    @Override
    public void delete(Integer pid) {
        ProductDao productDao=new ProductDaoImpl();
        productDao.delete(pid);
    }
}
