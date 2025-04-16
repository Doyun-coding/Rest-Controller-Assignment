package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.Project;
import com.nhnacademy.daily.model.ProjectCreateCommand;
import com.nhnacademy.daily.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> getProjects(Pageable pageable) {
        return projectService.getProjects();
    }

    @PostMapping("/projects")
    public ResponseEntity addProject(@RequestBody ProjectCreateCommand projectCreateCommand,
                                     Pageable pageable){
        projectService.createProject(projectCreateCommand);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects/{code}")
    public Project getProject(@PathVariable String code,
                              Pageable pageable) {
        return projectService.getProject(code);
    }

}
