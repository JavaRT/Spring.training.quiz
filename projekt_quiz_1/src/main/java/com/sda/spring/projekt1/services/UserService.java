package com.sda.spring.projekt1.services;

import com.sda.spring.projekt1.entities.ResultEntity;
import com.sda.spring.projekt1.repositories.ResultRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope
public class UserService {
    @Autowired
    private ResultRepository resultRepository;

    @Getter
    private int points;

    @Getter
    private String playerName;

    @Getter
    private Integer previousBestScore;

    private UUID gameSessionId;

    public void increasePoints() {
        this.points++;
    }

    public void updateUserResult() {
        final ResultEntity resultEntity = new ResultEntity(gameSessionId, playerName, points);
        resultRepository.save(resultEntity);
    }

    public void setPlayerNameAndLoadHighScore(String playerName) {
        this.playerName = playerName;
        this.previousBestScore = resultRepository.findTopScoreForUser(playerName);
        this.points = 0;
        this.gameSessionId = UUID.randomUUID();
    }
}