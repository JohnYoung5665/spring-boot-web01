package com.example.service.impl;

import com.example.mapper.DeptLogMapper;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    /*
    * REQUIRED：大部分情况下都是用该传播行为即可
    * REQUIRES_NEW：当我们不希望事务之间相互影响时，可以使用该传播行为。
    *       比如：下订单前需要记录日志，不论订单保存成功与否，都需要保证日志记录能够记录成功
    * */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
