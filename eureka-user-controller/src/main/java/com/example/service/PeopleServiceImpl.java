package com.example.service;

import com.example.entity.Department;
import com.example.mapper.PeopleMapper;
import com.example.vo.PeopleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService{
    @Resource
    private PeopleMapper peopleMapper;
    @Override
    public List<PeopleVo> getPeopleList(String name, Integer size, Integer start) {
        return peopleMapper.getPeopleList(name,size,start);
    }

    @Override
    public int getPeopleListCount(String name) {
        return peopleMapper.getPeopleListCount(name);
    }

    @Override
    public List<Department> getDepList() {
        return peopleMapper.getDepList();
    }

    @Override
    public int delPeople(Integer id) {
        return peopleMapper.delPeople(id);
    }
}
