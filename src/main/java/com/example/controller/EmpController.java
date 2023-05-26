package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


    /*
    * 查询emp员工表
    * */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }


    /*
    * 批量删除员工
    * */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工，ids：{}",ids);

        empService.delete(ids);

        return Result.success();
    }


    /**
     * 新增员工
     * @param emp
     * @return
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.insert(emp);
        return Result.success();
    }


    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据ID查询员工：{}",id);
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }


    /**
     * 修改员工数据
     * @param emp
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工数据,emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }







}
