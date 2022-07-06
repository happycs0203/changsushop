package com.changsu.project.changsushop.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @desc 로그인 폼
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class LoginForm {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
