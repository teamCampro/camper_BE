package com.campro.campsite.infrastructure.entity;


import com.campro.campsite.domain.Campsite;
import com.campro.campsite.presentation.request.CampsiteAddRequest;
import com.campro.onboarding.infrastructure.entity.ChoiceEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "campsite")
public class CampsiteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long owner_id;
    private String name;

    private String line_intro;

    private String intro;

    private String feature;

    private String induty;

    private String addr;

    private String addr1;

    private BigDecimal map_x;

    private BigDecimal map_y;

    private String tel;

    private String homepage;

    private String reserveUrl;

    private String reserveApp;

    private Long gnrlSite;

    private Long autoSite;

    private Long glampSite;

    private Long caravSite;

    private String operPeriod;

    private String operDate;

    private String sbrsCl;

    private String posblFcltyCl;

    private String thema;
    private String animalCmg;

    private String firstImgUrl;

    private Long likes;

    private BigDecimal star;

    private Date create_at;

    private Date update_at;

    @OneToMany(mappedBy = "campsite_id",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<CampsiteKeywordEntity> campsite_keyword;

    @OneToMany(mappedBy = "campsite_id",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<CampsiteImgEntity> campsite_img;

    public CampsiteEntity(Long owner_id,String name, String line_intro, String intro, String feature, String induty, String addr, String addr1 ,BigDecimal map_x, BigDecimal map_y, String tel, String homepage, String reserveUrl, String reserveApp, Long gnrlSite, Long autoSite, Long glampSite, Long caravSite, String operPeriod, String operDate, String sbrsCl, String posblFcltyCl, String thema, String animalCmg, String firstImgUrl, Long likes, BigDecimal star, Date create_at, Date update_at) {
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
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public CampsiteEntity() {
    }



    public Long getId() {
        return id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public String getLine_intro() {
        return line_intro;
    }

    public String getIntro() {
        return intro;
    }

    public String getFeature() {
        return feature;
    }

    public String getInduty() {
        return induty;
    }

    public String getAddr() {
        return addr;
    }

    public String getAddr1() {
        return addr1;
    }

    public BigDecimal getMap_x() {
        return map_x;
    }

    public BigDecimal getMap_y() {
        return map_y;
    }

    public String getTel() {
        return tel;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getReserveUrl() {
        return reserveUrl;
    }

    public String getReserveApp() {
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

    public String getOperPeriod() {
        return operPeriod;
    }

    public String getOperDate() {
        return operDate;
    }

    public String getSbrsCl() {
        return sbrsCl;
    }

    public String getPosblFcltyCl() {
        return posblFcltyCl;
    }

    public String getThema() {
        return thema;
    }

    public String getAnimalCmg() {
        return animalCmg;
    }

    public String getFirstImgUrl() {
        return firstImgUrl;
    }

    public Long getLikes() {
        return likes;
    }

    public BigDecimal getStar() {
        return star;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public List<CampsiteKeywordEntity> getCampsite_keyword() {
        return campsite_keyword;
    }

    public List<CampsiteImgEntity> getCampsite_img() {
        return campsite_img;
    }

    public static CampsiteEntity from(Campsite campsite){
        return new CampsiteEntity(campsite.owner_id()
                ,campsite.name(),
                campsite.line_intro(),
                campsite.intro(),
                campsite.feature(),
                campsite.induty(),
                campsite.addr(),
                campsite.addr1(),
                campsite.mapX(),
                campsite.mapY(),
                campsite.tel(),
                campsite.homepage(),
                campsite.reserveUrl(),
                campsite.reserveApp(),
                campsite.gnrlSite(),
                campsite.autoSite(),
                campsite.glampSite(),
                campsite.caravSite(),
                campsite.operPeriod(),
                campsite.operDate(),
                campsite.sbrsCl(),
                campsite.posblFcltyCl(),
                campsite.thema(),
                campsite.animalCmg(),
                "",
                0L,
                BigDecimal.ZERO,
                null,
                null);
    }

    public void chgFirstimg(String uploadUrl){
        this.firstImgUrl = uploadUrl;
    }

    public void updateCampsite(CampsiteAddRequest request){
        this.owner_id = request.getOwner_id();
        this.name = request.getName();
        this.line_intro = request.getLine_intro();
        this.intro = request.getIntro();
        this.induty = request.getInduty();
        this.addr = request.getAddr();
        this.addr1 = request.getAddr1();
        this.map_x = request.getMapX();
        this.map_y = request.getMapY();
        this.tel = request.getTel();
        this.homepage = request.getHomepage();
        this.reserveUrl = request.getReserveUrl();
        this.reserveApp = request.getReserveApp();
        this.operDate = request.getOperDate();
        this.sbrsCl = request.getSbrsCl();
        this.posblFcltyCl = request.getPosblFcltyCl();
        this.thema = request.getThema();
        this.animalCmg = request.getAnimalCmg();
        this.update_at = new Date();

    }



}
