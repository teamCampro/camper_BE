package com.campro.campsite.application.DTO;

import com.campro.campsite.infrastructure.entity.CampsiteEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampsiteFindDTO {
    private final Long id;

    private final Long owner_id;

    private final String name;

    private final String line_intro;
    private final String intro;

    private final String feature;
    private final String induty;

    private final String addr;

    private final String addr1;


    private final BigDecimal map_x;

    private final BigDecimal map_y;

    private final String tel;

    private final String homepage;

    private final String reserveUrl;

    private final String reserveApp;

    private final Long gnrlSite;

    private final Long autoSite;

    private final Long glampSite;
    private final Long caravSite;

    private final String operPeriod;

    private final String operDate;

    private final String sbrsCl;

    private final String posblFcltyCl;

    private final String thema;

    private final String animalCmg;

    private final String firstImgUrl;

    private final Long likes;

    private final BigDecimal star;


    public Long getId() {
        return id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public final String getName() {
        return name;
    }

    public final String getLine_intro() {
        return line_intro;
    }

    public final String getIntro() {
        return intro;
    }

    public final String getFeature() {
        return feature;
    }

    public final String getInduty() {
        return induty;
    }

    public final String getAddr() {
        return addr;
    }

    public final String getAddr1() {
        return addr1;
    }

    public BigDecimal getMap_x() {
        return map_x;
    }

    public BigDecimal getMap_y() {
        return map_y;
    }

    public final String getTel() {
        return tel;
    }

    public final String getHomepage() {
        return homepage;
    }

    public final String getReserveUrl() {
        return reserveUrl;
    }

    public final String getReserveApp() {
        return reserveApp;
    }

    public Long getGnrlSite() {
        return gnrlSite;
    }

    public Long getAutoSite() {
        return autoSite;
    }

    public Long getGlampSite() {
        return glampSite;
    }

    public Long getCaravSite() {
        return caravSite;
    }

    public final String getOperPeriod() {
        return operPeriod;
    }

    public final String getOperDate() {
        return operDate;
    }

    public final String getSbrsCl() {
        return sbrsCl;
    }

    public final String getPosblFcltyCl() {
        return posblFcltyCl;
    }

    public final String getThema() {
        return thema;
    }

    public final String getAnimalCmg() {
        return animalCmg;
    }

    public final String getFirstImgUrl() {
        return firstImgUrl;
    }

    public Long getLikes() {
        return likes;
    }

    public BigDecimal getStar() {
        return star;
    }

    public CampsiteFindDTO(Long id,Long owner_id ,final String name, final String line_intro, final String intro, final String feature, final String induty, final String addr, final String addr1, BigDecimal map_x, BigDecimal map_y, final String tel, final String homepage, final String reserveUrl, final String reserveApp, Long gnrlSite, Long autoSite, Long glampSite, Long caravSite, final String operPeriod, final String operDate, final String sbrsCl, final String posblFcltyCl, final String thema, final String animalCmg, final String firstImgUrl, Long likes, BigDecimal star) {
        this.id = id;
        this.owner_id = owner_id;
        this.name = name;
        this.line_intro = line_intro;
        this.intro = intro;
        this.feature = feature;
        this.induty = induty;
        this.addr = addr;
        this.addr1 = addr1;
        this.map_x = map_x;
        this.map_y = map_y;
        this.tel = tel;
        this.homepage = homepage;
        this.reserveUrl = reserveUrl;
        this.reserveApp = reserveApp;
        this.gnrlSite = gnrlSite;
        this.autoSite = autoSite;
        this.glampSite = glampSite;
        this.caravSite = caravSite;
        this.operPeriod = operPeriod;
        this.operDate = operDate;
        this.sbrsCl = sbrsCl;
        this.posblFcltyCl = posblFcltyCl;
        this.thema = thema;
        this.animalCmg = animalCmg;
        this.firstImgUrl = firstImgUrl;
        this.likes = likes;
        this.star = star;
    }

    public static CampsiteFindDTO toDTO(CampsiteEntity entity){
        return new CampsiteFindDTO(entity.getId(),entity.getOwner_id(), entity.getName(), entity.getLine_intro(), entity.getIntro(), entity.getFeature(),
                entity.getInduty(), entity.getAddr(), entity.getAddr1(), entity.getMap_x(), entity.getMap_y(), entity.getTel(),
                entity.getHomepage(), entity.getReserveUrl(), entity.getReserveApp(), entity.getGnrlSite(), entity.getAutoSite(),
                entity.getGlampSite(), entity.getCaravSite(), entity.getOperPeriod(), entity.getOperDate(), entity.getSbrsCl(),
                entity.getPosblFcltyCl(), entity.getThema(), entity.getAnimalCmg(), entity.getFirstImgUrl(), entity.getLikes(),entity.getStar());
    }

    public static List<CampsiteFindDTO> toDTOList(List<CampsiteEntity> list){
        List<CampsiteFindDTO> dtoList = new ArrayList<>();
        for (CampsiteEntity entity : list) {
                dtoList.add(toDTO(entity));
        }

        return dtoList;
    }

}
