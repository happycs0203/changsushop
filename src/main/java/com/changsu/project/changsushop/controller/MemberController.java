package com.changsu.project.changsushop.controller;


import com.changsu.project.changsushop.controller.dto.MemberSearchCondition;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.MemberUpdateForm;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import com.changsu.project.changsushop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import java.util.regex.Pattern;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/members")
    public String members(Model model, @Validated @ModelAttribute("memberSearch") MemberSearchCondition condition, Pageable pageable) {

        System.out.println(pageable.getPageSize());
        System.out.println("pageable = " + pageable.getPageNumber());
        System.out.println("pageable = " + pageable.getOffset());
        Page<MemberForm> members = memberRepository.searchPageByCondition(condition, pageable);

        System.out.println("condition.getSearchText() = " + condition.getSearchText());
        List<MemberForm> content = members.getContent();
        System.out.println("content = " + content.size());
        model.addAttribute("members", members);

        return "members/memberList";
    }


    @GetMapping("/members/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("member", new MemberForm(member));
        return "members/member";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberSaveForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Validated @ModelAttribute("memberForm") MemberSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.error("errors={} ", bindingResult);
            return "members/createMemberForm";
        }

        Long memberId = memberService.save(form);

        if(memberId == null){
            bindingResult.rejectValue("email", null, null, "이메일 중복 : 다른 이메일을 입력해주세요.");
            return "members/createMemberForm";
        }


        redirectAttributes.addAttribute("memberId", memberId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/members/{memberId}";

    }

    @GetMapping("/members/{memberId}/update")
    public String updateForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("memberForm", new MemberUpdateForm().memberToMemberUpdateForm(member));
        return "members/updateMemberForm";
    }

    @PostMapping("/members/{memberId}/update")
    public String update(@PathVariable("memberId") Long memberId, @Validated @ModelAttribute("memberForm") MemberUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";

        //현재 비밀번호와 같은지 확인

        if (memberService.checkPassword(form) == false) {
            bindingResult.rejectValue("password", null, null, "현재 비밀번호가 일치 하지 않습니다.");
        }


        //비밀번호가 1개라도 있으면 정규식 확인 및 일치 확인
        if (StringUtils.hasText(form.getPasswordNew()) || StringUtils.hasText(form.getPasswordNewConf())) {

            if (form.passwordCheck() == false) {
                bindingResult.rejectValue("passwordNewConf", null, null, "새 비밀번호가 일치 하지 않습니다.");
                bindingResult.rejectValue("passwordNew", null, null, "새 비밀번호가 일치 하지 않습니다.");
            } else {
                if (Pattern.matches(regex, form.getPasswordNew()) == false) {
                    bindingResult.rejectValue("passwordNew", null, null, "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
                }

                if (Pattern.matches(regex, form.getPasswordNewConf()) == false) {
                    bindingResult.rejectValue("passwordNewConf", null, null, "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
                }
            }
        }


        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.error("errors={} ", bindingResult);
            return "members/updateMemberForm";
        }

        Long updateMemberId = memberService.update(form);

        redirectAttributes.addAttribute("memberId", updateMemberId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/members/{memberId}";

    }

}
