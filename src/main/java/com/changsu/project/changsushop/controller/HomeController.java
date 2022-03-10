package com.changsu.project.changsushop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }

    @PostMapping("/changeLocale")
    @ResponseBody
    public String changeLocale(@RequestParam("language") String language, HttpServletRequest request) {

        System.out.println(language);
        HttpSession session = request.getSession();
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(language));

        String requestURI = "/";

        return requestURI;
    }

}
