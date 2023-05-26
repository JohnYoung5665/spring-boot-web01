package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper //在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器管理
public interface DeptMapper {
    /*
    * 查询全部部门数据
    * */
    @Select("select * from dept")
    List<Dept> list();


    /*
     * 根据 ID 删除部门数据
     * */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);


    /*
     * 新增部门
     * */
    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);





    /*
     * 根据 ID 修改部门
     * */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);


    /*
     * 根据ID查询部门
     * */
    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);


    /**
     * 根据deptId删除部门员工
     * @param id
     */
    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptId(Integer id);
}
