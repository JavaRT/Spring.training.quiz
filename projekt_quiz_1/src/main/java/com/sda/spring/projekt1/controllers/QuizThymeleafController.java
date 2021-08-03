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

import javax.validation.Valid;

@Controller
public class QuizThymeleafController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        final UserNameFormDTO formDTO = new UserNameFormDTO();
        if (principal != null) {
            formDTO.setName(principal.getAttribute("name"));
        }
        model.addAttribute("formDto", formDTO);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/startGame")
    public String postStartGame(@Valid UserNameFormDTO formDto) {
        userService.setPlayerNameAndLoadHighScore(formDto.getName());
        quizService.reset();
        return "redirect:/game";
    }

    @RequestMapping("/game")
    public String game(Model model) {
        model.addAttribute("playerName", userService.getPlayerName());
        model.addAttribute("points", userService.getPoints());
        model.addAttribute("previousBestScore", userService.getPreviousBestScore());

        final QuestionDTO q = quizService.getRandomQuestion();
        if (q == null) {
            return "thanks";
        }
        model.addAttribute("question", q);
        model.addAttribute("formDto", new UserAnswerFormDTO());
        return "game";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/game")
    public String postGame(@Valid UserAnswerFormDTO formDto) {
        System.out.println("Uzytkownik wybrał " + formDto);
        quizService.checkAnswerAndAddPoints(formDto.getAnswer());
        return "redirect:/game";
    }
}