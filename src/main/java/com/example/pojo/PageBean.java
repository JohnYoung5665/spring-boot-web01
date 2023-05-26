package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* 分页查询结果封装类
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
