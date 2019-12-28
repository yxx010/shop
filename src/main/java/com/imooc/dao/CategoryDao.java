package com.imooc.dao;

import com.imooc.domain.Category;

import java.sql.Connection;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

    void save(Category category);

    Category findOne(Integer cid);

    void update(Category category);

    void delete(Integer cid);

    void delete(Connection connection, Integer cid);
}
