package com.imooc.domain;

import lombok.Data;

import java.util.List;
@Data
public class PageBean<T> {
    private int page;
    private int limit;
    private int totalCount;
    private int totalPage;
    private List<T> list;
}
