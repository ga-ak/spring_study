package com.myspring.pro28.ex03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@EnableAsync
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/sendMail.do", method = RequestMethod.GET)
    public void sendSimpleMail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //mailService.sendMail("gghg0000@naver.com", "테스트메일", "안녕하세요");
        mailService.sendPreConfiguredMail("이건 프리컨피겨드메일");
        out.print("메일보냈다리");
    }
}
