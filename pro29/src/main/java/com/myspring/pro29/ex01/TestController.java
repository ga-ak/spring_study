package com.myspring.pro29.ex01;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test/*")
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello REST";
    }

    @RequestMapping("/member")
    public MemberVO member() {
        MemberVO vo = new MemberVO();
        vo.setId("kch2889");
        vo.setPwd("1234");
        vo.setName("김철호");
        vo.setEmail("gghg0000@gmail.com");
        return vo;
    }

    @RequestMapping("/memberList")
    public List<MemberVO> memberList() {
        List<MemberVO> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MemberVO vo = new MemberVO();
            vo.setId("kch2889"+i);
            vo.setPwd("1234"+i);
            vo.setName("김철호"+i);
            vo.setEmail("gghg0000@gmail.com"+i);
            list.add(vo);
        }

        return list;
    }

    @RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
    public int notice(@PathVariable("num") int num) throws Exception {
        return num;
    }
}
