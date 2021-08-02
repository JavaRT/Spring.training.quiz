package com.sda.spring.projekt1.services;

import com.sda.spring.projekt1.dtos.AddNewQuestionFormDTO;
import com.sda.spring.projekt1.dtos.QuestionDTO;
import com.sda.spring.projekt1.entities.QuestionEntity;
import com.sda.spring.projekt1.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public void addNewQuestion(AddNewQuestionFormDTO formDto) {
        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setText(formDto.getText());
        final ArrayList<String> answers = new ArrayList<>();
        answers.add(formDto.getAnswer1());
        answers.add(formDto.getAnswer2());
        questionEntity.setAnswers(answers);

        questionRepository.save(questionEntity);
    }

    public QuestionDTO fetchRandomQuestionFromDatabase() {
        final QuestionEntity questionFromDB = questionRepository.findRandomQuestion();
        return new QuestionDTO(questionFromDB.getText(), questionFromDB.getAnswers());
    }

}