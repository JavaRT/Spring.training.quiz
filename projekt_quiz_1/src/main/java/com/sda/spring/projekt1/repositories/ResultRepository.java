package com.sda.spring.projekt1.repositories;

import com.sda.spring.projekt1.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, UUID> {
    // SELECT TOP 1 * FROM RESULT_ENTITY WHERE username = ? ORDER BY points DESC
    Optional<ResultEntity> findFirstByUsernameOrderByPointsDesc(String username);

    // SELECT MAX(points) FROM RESULT_ENTITY WHERE username = ?
    @Query(value = "SELECT MAX(points) FROM ResultEntity WHERE username = :username")
    Integer findTopScoreForUser(String username);
}