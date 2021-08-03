package com.sda.spring.projekt1.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private final String text;
    private final List<String> answers;
}