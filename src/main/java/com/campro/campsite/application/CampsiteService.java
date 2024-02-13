package com.campro.campsite.application;

import com.campro.campsite.infrastructure.entity.CampsiteEntity;
import com.campro.campsite.presentation.request.CampsiteAddRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampsiteService {


    CampsiteEntity findCampsite(Long id);
    CampsiteEntity addCampsite(CampsiteAddRequest request, String uploadUrl);

    List<CampsiteEntity> searchCampsite(String addr, String reserve, String person, Pageable pageable);
    void updateCampsite(Long id, CampsiteAddRequest request);

    void delCampsite(Long id);

}
