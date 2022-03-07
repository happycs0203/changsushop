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

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request){
        return "/members/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        if (request.getParameter("error").equals("true")) {
            model.addAttribute("errorMessage", "아이디나 비밀번호가 맞지 않습니다. 확인해주세요.");
        }
        model.addAttribute("email", email);

        return "/members/login";
    }



}
