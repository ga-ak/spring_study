package com.spring.member.controller;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    MemberVO memberVO;

    @Override
    @RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
    public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        logger.debug("debug레벨 : viewName = " + viewName);
        List membersList = memberService.listMembers();
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("membersList", membersList);
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
    public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int result = memberService.addMember(memberVO);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
    public ModelAndView removeMember(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int result = memberService.removeMember(id);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("info") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        member = memberService.login(member);
        boolean isLogOn = member != null;
        ModelAndView mav = new ModelAndView();

        // 로그인 성공되었을 때 memberVO 세션에 저장함
        if (isLogOn) {

            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            logger.debug("login success member name :"+member.getName());
            mav.setViewName("redirect:/member/listMembers.do");

            // 로그인 실패했을 때 알림창 뜨기
        } else {
            logger.debug("login failed");
            rAttr.addAttribute("result", "loginFailed");
            mav.setViewName("redirect:/member/loginForm.do");
        }

        return mav;
    }

    @Override
    @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }

    @RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
    public ModelAndView form(@RequestParam(value = "result", required = false) String result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        logger.debug(viewName);
        ModelAndView mav = new ModelAndView(viewName);
        if (result != null) {
            mav.addObject("result", result);
        }
        return mav;
    }


    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        // context가 없지 않다면 시작지점을 context뒤로 지정함
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;

        // uri에 ';'가 있다면
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");

            // uri에 '?'가 있다면 : 쿼리스트링으로 정보전달한 경우 -> 뒷 내용 무시하기 위함
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
            // 위의 경우가 모두 아니라면 맨 끝까지 가져온다
        } else {
            end = uri.length();
        }

        // context 뒤 부터 쿼리스트링이 있다면 쿼리스트링 전까지 잘라서 viewName에 저장함
        String viewName = uri.substring(begin, end);

        // viewName에 '.'이 있다면 viewName 처음부터 마지막 '.'의 앞부분까지 잘라서 viewName에 저장
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }

        // viewName에 '/'이 있다면 마지막 '/'이 있는 위치부터 마지막 까지 잘라 viewName에 저장
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
        }
        return viewName;
    }
}
