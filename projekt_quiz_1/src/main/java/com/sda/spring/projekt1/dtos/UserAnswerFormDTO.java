package com.sda.spring.projekt1.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAnswerFormDTO {
    @NotNull
    private String answer;
}