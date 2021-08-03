package com.sda.spring.projekt1.repositories;

import com.sda.spring.projekt1.entities.QuestionEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    @Query("SELECT q FROM QuestionEntity q ORDER BY function('RAND')")
    List<QuestionEntity> findAllWithRandomOrder(Pageable pageable);

    default List<QuestionEntity> findRandomQuestions(int numberOfQuestions) {
        return findAllWithRandomOrder(PageRequest.of(0, numberOfQuestions));
    }
}