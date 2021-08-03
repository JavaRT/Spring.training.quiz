package com.sda.spring.projekt1.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class AddNewQuestionFormDTO {
    @NotNull
    @Length(min = 7)
    private String text;

    @NotNull
    @Length(min = 2)
    private String answer1;

    @NotNull
    @Length(min = 2)

    private String answer2;
}