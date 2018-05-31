package com.example.testmybatisplus;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.testmybatisplus.dao.ServerExceptionLogMapper;
import com.example.testmybatisplus.entity.ServerExceptionLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController extends ServiceImpl<ServerExceptionLogMapper, ServerExceptionLog> {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test(){
        Map<String, Object> params = new HashMap<String, Object>(){{
            put("status", 1);
        }};
        List<ServerExceptionLog> exceptionLogList = this.baseMapper.selectByMap(params);

        System.out.println("张文文查询："+exceptionLogList.size());
    }

}
