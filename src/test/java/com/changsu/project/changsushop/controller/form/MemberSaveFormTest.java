package com.changsu.project.changsushop.controller.form;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberSaveFormTest {

    @Test
    public void membermSaveTest() throws Exception{

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        //given
        MemberSaveForm build = MemberSaveForm.builder()
                .name("")
                .password("")
                .email("asdfasdf")
                .address("")
                .addressDetail("")
                .buildingName("")
                .zipcode("")
                .build();

        //when
        Set<ConstraintViolation<MemberSaveForm>> validate = validator.validate(build);
        for (ConstraintViolation<MemberSaveForm> violation : validate) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }

        //then

    }

}