package com.sda.spring.projekt1;

import com.sda.spring.projekt1.entities.QuestionEntity;
import com.sda.spring.projekt1.repositories.QuestionRepository;
import com.sda.spring.projekt1.repositories.ResultRepository;
import com.sda.spring.projekt1.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world! Witam na zajęciach");
    }
}