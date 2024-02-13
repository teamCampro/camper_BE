package com.campro.campsite.application;


import com.campro.campsite.domain.Campsite;
import com.campro.campsite.infrastructure.entity.CampsiteEntity;
import com.campro.campsite.infrastructure.repository.CampsiteRepository;
import com.campro.campsite.presentation.request.CampsiteAddRequest;
import com.campro.common.controller.exception.ApiException;
import com.campro.common.controller.exception.ErrorCode;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampsiteServiceImpl implements CampsiteService{

    private final CampsiteRepository campsiteRepository;

    public CampsiteServiceImpl(CampsiteRepository campsiteRepository) {
        this.campsiteRepository = campsiteRepository;
    }

    @Override
    public CampsiteEntity findCampsite(Long id) {
        return campsiteRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.NONE_DATA_EXCEPTION.getCode(), ErrorCode.NONE_DATA_EXCEPTION.getMessage()));
    }

    /**
     * 캠핑장 등록
     * @param request
     * @param uploadUrl
     * @return CampsiteEntity
     *
     */
    public CampsiteEntity addCampsite(CampsiteAddRequest request, String uploadUrl){

        CampsiteEntity campsiteEntity = CampsiteEntity.from(Campsite.addCampsite(request));
        campsiteEntity.chgFirstimg(uploadUrl);
        return campsiteRepository.save(campsiteEntity);

    }

    @Override
    public List<CampsiteEntity> searchCampsite(String addr, String reserve, String person, Pageable pageable) {
        List<CampsiteEntity> list = campsiteRepository.findByAddr1(addr,pageable);
        return list;
    }


    @Transactional
    public void updateCampsite(Long id, CampsiteAddRequest request){
        CampsiteEntity entity = campsiteRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.NONE_DATA_EXCEPTION.getCode(), ErrorCode.NONE_DATA_EXCEPTION.getMessage()));
        entity.updateCampsite(request);


    }

    public void delCampsite(Long id) {
        Optional<CampsiteEntity> campsite =campsiteRepository.findById(id);
        campsiteRepository.delete(campsite.orElseThrow(() -> new ApiException(ErrorCode.NONE_DATA_EXCEPTION.getCode(),ErrorCode.NONE_DATA_EXCEPTION.getMessage())));

    }







}
