package com.sda.spring.projekt1.controllers;

import com.sda.spring.projekt1.dtos.QuestionDTO;
import com.sda.spring.projekt1.dtos.UserAnswerFormDTO;
import com.sda.spring.projekt1.dtos.UserNameFormDTO;
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
public class QuizThymeleafController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            // uzytkownik jest zalogowany
            userService.setPlayerNameAndLoadHighScore(principal.getAttribute("name"));
            return "redirect:/game";
        }
        model.addAttribute("formDto", new UserNameFormDTO());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/startGame")
    public String postStartGame(UserNameFormDTO formDto) {
        userService.setPlayerNameAndLoadHighScore(formDto.getName());
        return "redirect:/game";
    }

    @RequestMapping("/game")
    public String game(Model model) {
        final QuestionDTO q = quizService.getRandomQuestion();
        model.addAttribute("question", q);
        model.addAttribute("formDto", new UserAnswerFormDTO());
        model.addAttribute("playerName", userService.getPlayerName());
        model.addAttribute("points", userService.getPoints());
        model.addAttribute("previousBestScore", userService.getPreviousBestScore());
        return "game";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/game")
    public String postGame(UserAnswerFormDTO formDto) {
        System.out.println("Uzytkownik wybrał " + formDto);
        quizService.checkAnswerAndAddPoints(formDto.getAnswer());
        return "redirect:/game";
    }
}