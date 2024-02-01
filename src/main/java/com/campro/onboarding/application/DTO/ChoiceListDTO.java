package com.campro.onboarding.application.DTO;

import com.campro.onboarding.infrastructure.entity.ChoiceEntity;


public class ChoiceListDTO {
    private Long id;

    private String text;

    public ChoiceListDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    public static ChoiceListDTO toDTO(ChoiceEntity entity){
        return new ChoiceListDTO(entity.getId(),entity.getText());
    }
}
