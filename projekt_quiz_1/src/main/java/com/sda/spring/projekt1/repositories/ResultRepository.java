package com.sda.spring.projekt1.repositories;

import com.sda.spring.projekt1.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, UUID> {
    Optional<ResultEntity> findFirstByUsernameOrderByPointsDesc(String username);
}