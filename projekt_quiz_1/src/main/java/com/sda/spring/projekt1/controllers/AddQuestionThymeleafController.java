package com.sda.spring.projekt1.controllers;

import com.sda.spring.projekt1.dtos.AddNewQuestionFormDTO;
import com.sda.spring.projekt1.dtos.QuestionDTO;
import com.sda.spring.projekt1.dtos.UserAnswerFormDTO;
import com.sda.spring.projekt1.dtos.UserNameFormDTO;
import com.sda.spring.projekt1.services.QuestionService;
import com.sda.spring.projekt1.services.QuizService;
import com.sda.spring.projekt1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddQuestionThymeleafController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/addQuestion")
    public String addQuestion(Model model) {
        model.addAttribute("formDto", new AddNewQuestionFormDTO());
        return "addQuestion";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addQuestion")
    public String postAddQuestion(AddNewQuestionFormDTO formDto) {
        questionService.addNewQuestion(formDto);
        return "redirect:/";
    }
}
