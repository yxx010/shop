package com.imooc.service.impl;

import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.PageBean;
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

    @Override
    public PageBean<Product> findByPage(int page) {
        PageBean<Product> pageBean=new PageBean<>();
        //封装当前页数
        pageBean.setPage(page);
        int limit=6;
        pageBean.setLimit(limit);
        //封装总的记录数
        ProductDao productDao=new ProductDaoImpl();
        int totalCount=productDao.findCount();
        pageBean.setTotalCount(totalCount);
        //总页数
        int totalPage=0;
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }else{
            totalPage=totalCount/limit+1;
        }
        pageBean.setTotalPage(totalPage);
        int begin=(page-1)*limit;
        List<Product> productList=productDao.findByPage(begin,limit);
        pageBean.setList(productList);
        return pageBean;
    }
}
