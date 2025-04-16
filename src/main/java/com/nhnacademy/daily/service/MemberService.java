package com.nhnacademy.daily.service;

import com.nhnacademy.daily.exception.DuplicateResourceException;
import com.nhnacademy.daily.exception.NotFoundResourceException;
import com.nhnacademy.daily.model.Locale;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Member:";

    public void createMember(MemberCreateCommand memberCreateCommand) {
        Object o = redisTemplate.opsForHash().get(HASH_NAME, memberCreateCommand.getId());
        if (o != null) {
            throw new DuplicateResourceException("이미 존재하는 아이디입니다.");
        }
        Member member = new Member(memberCreateCommand.getId(), memberCreateCommand.getName(), memberCreateCommand.getAge(), memberCreateCommand.getClazz());
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
    }

    public Member getMember(String memberId) {

        Object o = redisTemplate.opsForHash().get(HASH_NAME, memberId);
        if (o == null) {
            throw new NotFoundResourceException("해당 아이디에 맞는 아이디가 존재하지 않습니다.");
        }
        return (Member) o;
    }

    public List<Member> getMembers() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_NAME);
        List<Member> members = new ArrayList<>(entries.size());
        for (Object value : entries.values()) {
            members.add((Member) value);
        }
        return members;
    }

}
