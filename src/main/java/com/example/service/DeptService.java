package com.example.service;


import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {

    /*
    * 查询全部部门数据
    * */
    List<Dept> list();


    /*
     * 根据 ID 删除部门数据
     * */
    void delete(Integer id) throws Exception;


    /*
     * 新增部门
     * */
    void add(Dept dept);


    /*
     * 根据 ID 修改部门
     * */
    void update(Dept dept);



    /*
     * 根据ID查询部门
     * */
    Dept selectById(Integer id);
}
