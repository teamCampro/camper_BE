package com.campro.onboarding.infrastructure.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "onboarding")
public class OnboardingEntity {

    public OnboardingEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long board_id;

    @Column
    private String question;



    @OneToMany(mappedBy = "onboarding", cascade = CascadeType.ALL)
    private List<ChoiceEntity> choices;


    public Long getBoard_id() {
        return board_id;
    }

    public String getQuestion() {
        return question;
    }

    public List<ChoiceEntity> getChoices() {
        return choices;
    }
}
