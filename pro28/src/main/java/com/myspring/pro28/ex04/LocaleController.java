package com.myspring.pro28.ex04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("localeController")
public class LocaleController {

    @RequestMapping(value = "/test/locale.do", method = RequestMethod.GET)
    public String locale(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "locale";
    }
}
