package com.changsu.project.changsushop.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;
    
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    public void testMessage() throws Exception{
        //given
        String result = ms.getMessage("hello", null, null);
        String resultKor = ms.getMessage("hello", null, Locale.KOREA);
        String resultNoCode = ms.getMessage("no_code", null, "기본 메세지", null);
        String resultName = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        String resultEng = ms.getMessage("hello", null, Locale.ENGLISH);
        String resultEngName = ms.getMessage("hello.name", new Object[]{"Spring"}, Locale.ENGLISH);

        //then
        assertThat(result).isEqualTo("안녕");
        assertThat(resultKor).isEqualTo("안녕");
        assertThat(resultNoCode).isEqualTo("기본 메세지");
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
        assertThat(resultName).isEqualTo("안녕 Spring");
        assertThat(resultEng).isEqualTo("hello");
        assertThat(resultEngName).isEqualTo("hello Spring");

    }
    
    @Test
    public void testErrorMessage() throws Exception{
        //given
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("string = " + messageCode);
        }
        //codes를 new String[]{"required.item", "required"} 순서대로 찾는다. 디테일 -> 범용성
//         new ObjectError("item", new String[]{"required.item", "required"})
        Assertions.assertThat(messageCodes)
                .containsExactly(
                        "required.item",
                        "required"
                );

        String[] messageCodesFields = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class); //필드 타입까지
        for (String messageCodesField : messageCodesFields) {
            System.out.println("messageCodesField = " + messageCodesField);
        }
        //4개의 오류 메시지를 만들어준다.
//        messageCodesField = required.item.itemName 디테일
//        messageCodesField = required.itemName 2번째 디테일
//        messageCodesField = required.java.lang.String 3번째 디테일
//        messageCodesField = required 넚은범위
        //bindingResult.rejectValue("itemName", "required") -> rejectValue안에서 codeResolver를 생성한다. 그리고 new FieldError
        //new FieldError("item", "itemName", null, false, messageCodes, null, null)

        //when
        Assertions.assertThat(messageCodesFields)
                .containsExactly(
                        "required.item.itemName",
                        "required.item",
                        "required.java.lang.String",
                        "required"
                );
        //ErrorCodes를 가지고 메시지에서 찾을 수 있는 오류 코드를 다 가지고 온다.
    
    }

}
