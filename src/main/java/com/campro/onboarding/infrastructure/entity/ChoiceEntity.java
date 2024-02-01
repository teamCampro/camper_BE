package com.campro.onboarding.infrastructure.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "choice")
public class ChoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private OnboardingEntity onboarding;



    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public OnboardingEntity getOnboarding() {
        return onboarding;
    }
}
