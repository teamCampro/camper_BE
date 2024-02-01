package com.campro.onboarding.application.DTO;

import com.campro.onboarding.infrastructure.entity.ChoiceEntity;
import com.campro.onboarding.infrastructure.entity.OnboardingEntity;

import java.util.ArrayList;
import java.util.List;

public class OnboardingListDTO {
    private Long borad_id;
    private String question;

    private List<ChoiceListDTO> choices;

    public OnboardingListDTO(Long borad_id, String question, List<ChoiceListDTO> choices) {
        this.borad_id = borad_id;
        this.question = question;
        this.choices = choices;
    }

    public Long getBorad_id() {
        return borad_id;
    }

    public String getQuestion() {
        return question;
    }

    public List<ChoiceListDTO> getChoices() {
        return choices;
    }

    public static OnboardingListDTO toDTO(OnboardingEntity entity){
        List<ChoiceListDTO> choiceList = new ArrayList<>();
        for (ChoiceEntity choice : entity.getChoices()) {
            choiceList.add(ChoiceListDTO.toDTO(choice));
        }

        return new OnboardingListDTO(entity.getBoard_id(),entity.getQuestion(),choiceList);
    }
}
