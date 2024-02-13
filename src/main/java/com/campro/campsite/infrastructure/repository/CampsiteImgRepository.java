package com.campro.campsite.infrastructure.repository;

import com.campro.campsite.infrastructure.entity.CampsiteImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampsiteImgRepository extends JpaRepository<CampsiteImgEntity,Long> {


    @Query("select ci from CampsiteImgEntity ci where ci.campsite_id = :id and ci.origin_name = :original_name")
    Optional<CampsiteImgEntity> findByCampsiteId(Long id, String original_name);

}
