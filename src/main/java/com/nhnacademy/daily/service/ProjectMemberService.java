package com.nhnacademy.daily.service;

import com.nhnacademy.daily.exception.DuplicateResourceException;
import com.nhnacademy.daily.exception.NotFoundResourceException;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMemberService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MemberService memberService;


    private String HASH_NAME = "ProjectMember:";

    public void createProjectMember(String projectId, String memberId) {
        Object o = redisTemplate.opsForHash().get(HASH_NAME, projectId);
        List<Member> list;
        if (o == null) {
            list = new ArrayList<>();
        }
        else {
            list = (List<Member>) o;
        }

        Member member = memberService.getMember(memberId);
        list.add(member);

        redisTemplate.opsForHash().put(HASH_NAME, projectId, member);
    }

    public List<Member> getProjectMembers(String projectId) {
        Object o = redisTemplate.opsForHash().get(HASH_NAME, projectId);

        return (List<Member>) o;
    }

}
