package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.auth.dto.SessionMember;
import com.changsu.project.changsushop.controller.form.LoginForm;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.auth.interceptor.SessionConst;
import com.changsu.project.changsushop.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @desc 멤버 로그인 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * @desc 로그인 페이지 이동
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, Model model){

        String redirectURL = request.getParameter("redirectURL");
        model.addAttribute("redirectURL", redirectURL);

        return "members/login";
    }

    /**
     * @desc 로그인 로직
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String redirectURL = request.getParameter("redirectURL");
        if (request.getParameter("error").equals("true")) {
            model.addAttribute("errorMessage", "아이디나 비밀번호가 맞지 않습니다. 확인해주세요.");
        }
        model.addAttribute("email", email);
        model.addAttribute("redirectURL", redirectURL);

        return "members/login";
    }



}
