package com.sda.spring.projekt1.services;

import com.sda.spring.projekt1.dtos.QuestionDTO;
import com.sda.spring.projekt1.entities.QuestionEntity;
import com.sda.spring.projekt1.repositories.QuestionRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class QuizService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private QuestionDTO lastAskedQuestion = null;
    private String lastAskedQuestionCorrectAnswer = null;

    public QuestionDTO getRandomQuestion() {
        final QuestionDTO randomQuestion = questionService.fetchRandomQuestionFromDatabase();
        String correctAnswer = randomQuestion.getAnswers().get(0);

        lastAskedQuestion = randomQuestion;
        lastAskedQuestionCorrectAnswer = correctAnswer;

        Collections.shuffle(randomQuestion.getAnswers());
        return randomQuestion;
    }

    public void checkAnswerAndAddPoints(String answer) {
        if (lastAskedQuestionCorrectAnswer.equals(answer)) {
            System.out.println("Użytkownik odpowiedział dobrze");
            userService.increasePoints();
        } else {
            System.out.println("Użytkownik odpowiedział źle");
        }
        userService.updateUserResult();
    }
}


//    QuestionDTO dto = new QuestionDTO("Czy Ziemia jest płaska?",
//            Arrays.asList("Nie", "Tak", "Nie wiem", "To zależy"));
//    QuestionDTO dto2 = new QuestionDTO("Czy Ziemia okrąża Słońce?",
//            Arrays.asList("Tak", "Nie", "Nie wiem", "To zależy"));
//    QuestionDTO dto3 = new QuestionDTO("Ile wynosi prędkość światła?",
//            Arrays.asList("300000 km/s", "100 m/s", "1000000000000 km/h", "0"));