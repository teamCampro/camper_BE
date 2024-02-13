package com.campro.campsite.application;

import com.campro.campsite.infrastructure.entity.CampsiteKeywordEntity;
import com.campro.campsite.infrastructure.repository.CampsiteKeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CampsiteKeywordServiceImpl implements CampsiteKeywordService{

    private final CampsiteKeywordRepository campsiteKeywordRepository;

    public CampsiteKeywordServiceImpl(CampsiteKeywordRepository campsiteKeywordRepository) {
        this.campsiteKeywordRepository = campsiteKeywordRepository;
    }
    /**
     * 키워드 등록
     * @param campsiteId
     * @param keywords
     */
    public void addKeyword(Long campsiteId, List<String> keywords){
        for (String keyword : keywords) {
            CampsiteKeywordEntity entity = new CampsiteKeywordEntity(campsiteId, keyword,1L);
            campsiteKeywordRepository.save(entity);
        }
    }
}
