package com.changsu.project.changsushop.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class LoginForm {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
