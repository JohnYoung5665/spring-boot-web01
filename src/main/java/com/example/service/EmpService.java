package com.example.service;


import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /*
     * 分页查询
     * */
    PageBean page(Integer page, Integer pageSize,
                  String name, Short gender,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);


    /*
     * 批量删除员工
     * */
    void delete(List<Integer> ids);


    /**
     * 新增员工
     * @param emp
     */
    void insert(Emp emp);


    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Emp selectById(Integer id);


    /**
     * 修改员工数据
     * @param emp
     */
    void update(Emp emp);


    /**
     * 员工登录
     *
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
