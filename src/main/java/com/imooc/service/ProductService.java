package com.imooc.service;

import com.imooc.domain.PageBean;
import com.imooc.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findOne(Integer pid);

    void update(Product product);

    void delete(Integer pid);

    PageBean<Product> findByPage(int page);
}
