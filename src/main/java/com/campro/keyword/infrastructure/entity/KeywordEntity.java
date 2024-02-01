package com.campro.keyword.infrastructure.entity;


import com.campro.onboarding.infrastructure.entity.OnboardingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "keyword")
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;


    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
