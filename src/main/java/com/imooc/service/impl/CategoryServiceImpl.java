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
}
