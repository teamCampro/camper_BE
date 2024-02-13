package com.campro.campsite.application;

import com.campro.campsite.presentation.request.CampsiteImgRequest;

import java.util.List;

public interface CampsiteImgService {
    void addImage(Long campsiteId, List<CampsiteImgRequest> imgEntityList);
    void delImage(Long campsiteId, List<CampsiteImgRequest> imgList);

}
