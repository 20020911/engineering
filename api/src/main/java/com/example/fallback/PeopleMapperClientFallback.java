package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.Department;
import com.example.mapper.PeopleMapperClient;
import com.example.vo.PeopleVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleMapperClientFallback implements PeopleMapperClient {
    @Override
    public ResponseBean getPeopleList(String name, Integer start, Integer size) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getDepList() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean delPeople(Integer id) {
        return ResponseBean.error(600,"访问出错");
    }
}
