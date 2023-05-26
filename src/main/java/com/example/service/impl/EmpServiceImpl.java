package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    /*
     * 分页查询
     * */
    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        //1.获取总记录数
        Long count = empMapper.count();

        //2.获取分页查询结果列表
        //起始索引 = （页码 - 1）* 每页展示记录数
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);

        //3.封装PageBean对象
        PageBean pageBean = new PageBean(count,empList);

        return pageBean;
    }*/



    /*
    *PageHelper分页插件实现分页查询
    * */
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        //1.设置分页参数
        PageHelper.startPage(page,pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;

        //3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    /*
     * 批量删除员工
     * */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }


    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }


    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    @Override
    public Emp selectById(Integer id) {
        return empMapper.selectById(id);
    }


    /**
     * 修改员工数据
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }


    /**
     * 员工登录
     *
     * @param emp
     * @return
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.selectByUsernameAndPassword(emp);
    }


}
