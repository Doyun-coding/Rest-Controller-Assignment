package com.nhnacademy.daily.service;

import com.nhnacademy.daily.exception.DuplicateResourceException;
import com.nhnacademy.daily.exception.NotFoundResourceException;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberCreateCommand;
import com.nhnacademy.daily.model.Project;
import com.nhnacademy.daily.model.ProjectCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Project:";

    public void createProject(ProjectCreateCommand projectCreateCommand) {
        Object o = redisTemplate.opsForHash().get(HASH_NAME, projectCreateCommand.getCode());
        if (o != null) {
            throw new DuplicateResourceException("이미 존재하는 프로젝트 code 입니다.");
        }
        Project project = new Project(projectCreateCommand.getCode(), LocalDate.now(), projectCreateCommand.getProjectType());
        redisTemplate.opsForHash().put(HASH_NAME, project.getCode(), project);
    }

    public Project getProject(String code) {

        Object o = redisTemplate.opsForHash().get(HASH_NAME, code);
        if (o == null) {
            throw new NotFoundResourceException("해당 code 와 일치하는 프로젝트가 존재하지 않습니다.");
        }
        return (Project) o;
    }

    public List<Project> getProjects() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_NAME);
        List<Project> projects = new ArrayList<>(entries.size());
        for (Object value : entries.values()) {
            projects.add((Project) value);
        }
        return projects;
    }

}
