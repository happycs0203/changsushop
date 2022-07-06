package com.changsu.project.changsushop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @desc return 값
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int count;
    private T data;
}
