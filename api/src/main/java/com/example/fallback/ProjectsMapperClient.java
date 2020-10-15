package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.Project;
import com.example.mapper.ProjectMapperClient;
import org.springframework.stereotype.Component;

@Component
public class ProjectsMapperClient implements ProjectMapperClient {
    @Override
    public ResponseBean getList(String manager, String name, Integer state, int start, int size) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getProject(Integer id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean addProject(Project project) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean updProject(Project project) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean delProject(Integer id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean stateList() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean projectList() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean userList() {
        return ResponseBean.error(600,"访问出错");
    }
}
