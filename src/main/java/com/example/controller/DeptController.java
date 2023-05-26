package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j  //log日志
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired  //注入service
    private DeptService deptService;

    /*
    * 查询dept表
    * */
    //指定请求方式为GET
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        //log日志输出
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        //响应数据
        return Result.success(deptList);
    }



    /**
     * 根据 ID 删除部门数据
     * @param id
     * @return
     * @throws Exception
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据ID删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }


    /*
    * 新增部门
    * */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }


    /*
     * 根据ID查询部门
     * */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据ID查询部门");
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }


    /*
    * 根据 ID 修改部门
    * */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("根据ID修改部门");
        deptService.update(dept);
        return Result.success();
    }



}
