package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @GetMapping("/projects/{projectId}/member")
    public List<Member> getProjectMembers(@PathVariable String projectId) {
        return projectMemberService.getProjectMembers(projectId);
    }


    @PostMapping("/projects/{projectId}/member/{memberId}")
    public void addProjectMember(@PathVariable String projectId,
                                 @PathVariable String memberId) {
        projectMemberService.createProjectMember(projectId, memberId);
    }

}
