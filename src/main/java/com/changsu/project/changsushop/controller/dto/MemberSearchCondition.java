package com.changsu.project.changsushop.controller.dto;

import lombok.Data;

/**
 * @desc 멤버 조회 입력
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class MemberSearchCondition {

    private String searchType;
    private String searchText;

}
