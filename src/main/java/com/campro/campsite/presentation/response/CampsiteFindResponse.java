package com.campro.campsite.presentation.response;

import com.campro.campsite.application.DTO.CampsiteFindDTO;
import com.campro.campsite.domain.Campsite;
import com.campro.campsite.infrastructure.entity.CampsiteEntity;

public record CampsiteFindResponse(CampsiteFindDTO campsite) {
    public static CampsiteFindResponse from(CampsiteFindDTO campsite){
        return new CampsiteFindResponse(campsite);
    }

}
