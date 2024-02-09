package com.campro.onboarding.application.DTO;

import com.campro.onboarding.infrastructure.entity.ChoiceEntity;
import com.campro.onboarding.infrastructure.entity.OnboardingEntity;

import java.util.ArrayList;
import java.util.List;

public class OnboardingListDTO {
    private final Long board_id;
    private final String question;

    private final List<ChoiceListDTO> choices;

    public OnboardingListDTO(Long boardId, String question, List<ChoiceListDTO> choices) {
        board_id = boardId;
        this.question = question;
        this.choices = choices;
    }

    public Long getBoard_id() {
        return board_id;
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
