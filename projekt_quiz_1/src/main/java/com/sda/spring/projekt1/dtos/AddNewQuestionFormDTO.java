package com.sda.spring.projekt1.dtos;

import lombok.Data;

@Data
public class AddNewQuestionFormDTO {
    private String text;
    private String answer1;
    private String answer2;
}