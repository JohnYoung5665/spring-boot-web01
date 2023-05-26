package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service    //代表要将当前实现类交给 ioc容器管理 成为ioc容器当中的 bean对象
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptLogService deptLogService;

    /*
     * 查询全部部门数据
     * */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    /*
     * 根据 ID 删除部门数据
     * */
    //rollbackFor属性用于控制出现何种异常类型，回滚事务
    @Transactional//(rollbackFor = Exception.class)  //spring事务管理
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.delete(id);

            //模拟异常发生
            //int i = 1/0;
            /*if (true){
                throw new Exception("出错啦。。。");
            }*/

            deptMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是" +id+ "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    /*
     * 根据 ID 修改部门
     * */
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


    /*
     * 根据ID查询部门
     * */
    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }
}
