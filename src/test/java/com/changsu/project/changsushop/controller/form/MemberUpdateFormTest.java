package com.changsu.project.changsushop.controller.form;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberUpdateFormTest {

    @Test
    public void memberUpdate() throws Exception{
        //given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        //when

        MemberUpdateForm build = MemberUpdateForm.builder()
                .id(1L)
                .name("함창수")
                .password("Hamchangsu020#")
                .passwordNew("Hamchangsu020#")
                .passwordNewConf("Hamchangsu020#")
                .address("서울시")
                .addressDetail("목동서로130")
                .buildingName("목동4단지아파트")
                .zipcode("123555")
                .build();

        Set<ConstraintViolation<MemberUpdateForm>> validate = validator.validate(build);
        //then
        for (ConstraintViolation<MemberUpdateForm> violation : validate) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
        
        

    }

}