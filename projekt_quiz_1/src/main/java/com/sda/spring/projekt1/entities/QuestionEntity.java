package com.sda.spring.projekt1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String text;

    // dzięki EAGER zawsze pobierzemy wszystkie odpowiedzi, bez potrzeby używania dodatkowych zapytań
    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> answers;

}