package com.imooc.dao;

import com.imooc.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}
