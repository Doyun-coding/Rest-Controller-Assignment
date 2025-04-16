package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberCreateCommand;
import com.nhnacademy.daily.service.DoorayService;
import com.nhnacademy.daily.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DoorayService doorayService;

    @GetMapping("/members")
    public List<Member> getMembers(Pageable pageable) {
        return memberService.getMembers();
    }

    @PostMapping("/members")
    public ResponseEntity addMember(@RequestBody MemberCreateCommand memberCreateCommand,
                                    Pageable pageable){
        memberService.createMember(memberCreateCommand);
        doorayService.sendMessage();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/{memberId}")
    public Member getMembers(@PathVariable String memberId,
                             Pageable pageable){
        return memberService.getMember(memberId);
    }



}
