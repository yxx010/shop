package com.imooc.service.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.dao.impl.CategoryDaoImpl;
import com.imooc.domain.Category;
import com.imooc.service.CategoryService;
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
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.delete(cid);
    }
}
