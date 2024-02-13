package com.campro.campsite.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="campsite_img")
public class CampsiteImgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campsite_id",insertable = false, updatable = false)
    private CampsiteEntity campsite;

    @Column(name="campsite_id")
    private Long campsite_id;
    private String origin_name;

    private String upload_url;

    private Date create_at;


    public CampsiteImgEntity() {
    }

    public CampsiteImgEntity(Long campsite_id, String origin_name, String upload_url, Date create_at) {
        this.campsite_id = campsite_id;
        this.origin_name = origin_name;
        this.upload_url = upload_url;
        this.create_at = create_at;
    }

    public Long getId() {
        return id;
    }


}
