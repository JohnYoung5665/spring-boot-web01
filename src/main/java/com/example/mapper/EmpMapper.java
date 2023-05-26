package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper //在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理
public interface EmpMapper {

    /*
    * 查询总记录数
    * */
    /*@Select("select count(*) from emp")
    Long count();*/


    /*
    *
    * */
    /*@Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);*/


    /*
    * 员工信息查询
    * */
    //@Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


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
    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);



    void update(Emp emp);

    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
