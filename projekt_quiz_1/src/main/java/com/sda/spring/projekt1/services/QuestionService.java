package com.sda.spring.projekt1.services;

import com.sda.spring.projekt1.dtos.QuestionDTO;
import com.sda.spring.projekt1.entities.QuestionEntity;
import com.sda.spring.projekt1.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public void addNewQuestion() {
    }

    public QuestionDTO fetchRandomQuestionFromDatabase() {
        final long numberOfQuestionsInDB = questionRepository.count();

        final Random random = new Random();
        final int selectedQuestionId = random.nextInt((int)numberOfQuestionsInDB) + 1;

        final Optional<QuestionEntity> questionFromDBOptional = questionRepository.findById(selectedQuestionId);
        if (questionFromDBOptional.isEmpty()) {
            throw new RuntimeException("Question with id " + selectedQuestionId + " not found!");
        }
        final QuestionEntity questionFromDB = questionFromDBOptional.get();

        return new QuestionDTO(questionFromDB.getText(), questionFromDB.getAnswers());
    }

}