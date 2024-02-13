package com.campro.campsite.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name="campsite_keyword")
public class CampsiteKeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campsite_id",insertable = false, updatable = false)
    private CampsiteEntity campsite;

    @Column(name="campsite_id")
    private Long campsite_id;

    private String keyword;

    private Long count;




    public CampsiteKeywordEntity(Long campsite_id, String keyword, Long count) {
        this.campsite_id = campsite_id;
        this.keyword = keyword;
        this.count = count;
    }

    public CampsiteKeywordEntity() {

    }

    public Long getId() {
        return id;
    }


}
