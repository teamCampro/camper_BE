package com.campro.campsite.infrastructure.repository;

import com.campro.campsite.infrastructure.entity.CampsiteEntity;
import com.campro.campsite.infrastructure.entity.CampsiteKeywordEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampsiteRepository extends JpaRepository<CampsiteEntity,Long> {

    List<CampsiteEntity> findAll();

    List<CampsiteEntity> findByAddr1(String addr, Pageable pageable);

}
