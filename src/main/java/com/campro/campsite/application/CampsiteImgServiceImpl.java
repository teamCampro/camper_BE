package com.campro.campsite.application;

import com.campro.campsite.infrastructure.entity.CampsiteImgEntity;
import com.campro.campsite.infrastructure.repository.CampsiteImgRepository;
import com.campro.campsite.presentation.request.CampsiteImgRequest;
import com.campro.common.exception.ApiException;
import com.campro.common.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CampsiteImgServiceImpl implements CampsiteImgService{


    private final CampsiteImgRepository campsiteImgRepository;

    public CampsiteImgServiceImpl(CampsiteImgRepository campsiteImgRepository) {
        this.campsiteImgRepository = campsiteImgRepository;
    }

    /**
     * campsite에 이미지 list 추가
     * @param campsiteId
     * @param imgEntityList
     */
    public void addImage(Long campsiteId, List<CampsiteImgRequest> imgEntityList){
        for (CampsiteImgRequest campsiteImgRequest : imgEntityList) {
            CampsiteImgEntity entity = new CampsiteImgEntity(campsiteId, campsiteImgRequest.getOriginName(), campsiteImgRequest.getUploadUrl(),new Date());
            campsiteImgRepository.save(entity);
        }
    }

    public void delImage(Long campsiteId, List<CampsiteImgRequest> imgList){
        for (CampsiteImgRequest campsiteImgRequest : imgList) {
            CampsiteImgEntity entity = campsiteImgRepository.findByCampsiteId(campsiteId,campsiteImgRequest.getOriginName())
                    .orElseThrow(()-> new ApiException(ErrorCode.NONE_DATA_EXCEPTION.getCode(), ErrorCode.NONE_DATA_EXCEPTION.getMessage()));
            campsiteImgRepository.delete(entity);
        }

    }
}
