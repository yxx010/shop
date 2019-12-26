package com.imooc.domain;

import lombok.Data;

@Data
public class Product {
    private Integer pid;
    private String pname;
    private String author;
    private Double price;
    private String description;
    private String filename;
    private String path;
    private Integer cid;
}
