package com.sda.spring.projekt1.services;

import com.sda.spring.projekt1.dtos.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;

@Service
@SessionScope
public class QuizService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private List<QuestionDTO> selectedQuestions = null;
    private Integer questionToBeAskedIndex = null;
    private String lastAskedQuestionCorrectAnswer = null;

    public QuestionDTO getRandomQuestion() {
        if (selectedQuestions == null) {
            this.selectedQuestions = questionService.selectRandomQuestions(5);
            this.questionToBeAskedIndex = 0;
        }
        if (this.questionToBeAskedIndex >= this.selectedQuestions.size()) {
            // koniec quizu, nie mamy wiecej pytan
            return null;
        }
        final QuestionDTO randomQuestion = this.selectedQuestions.get(this.questionToBeAskedIndex);
        this.questionToBeAskedIndex++;

        lastAskedQuestionCorrectAnswer = randomQuestion.getAnswers().get(0);

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

    public void reset() {
        this.selectedQuestions = null;
        this.questionToBeAskedIndex = null;
        this.lastAskedQuestionCorrectAnswer = null;
    }
}