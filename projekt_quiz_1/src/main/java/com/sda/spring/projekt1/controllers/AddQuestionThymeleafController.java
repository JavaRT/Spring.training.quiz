package com.sda.spring.projekt1.controllers;

import com.sda.spring.projekt1.dtos.AddNewQuestionFormDTO;
import com.sda.spring.projekt1.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AddQuestionThymeleafController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/addQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public String addQuestion(Model model) {
        model.addAttribute("formDto", new AddNewQuestionFormDTO());
        return "addQuestion";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public String postAddQuestion(@Valid AddNewQuestionFormDTO formDto) {
        questionService.addNewQuestion(formDto);
        return "redirect:/";
    }
}