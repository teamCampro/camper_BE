package com.campro.campsite.presentation.request;

import java.math.BigDecimal;

public class CampsiteAddRequest {


    private final Long owner_id;
    private final String name;
    private final String line_intro;
    private final String intro;

    private final String induty;

    private final String addr;
    private final String addr1;

    private final BigDecimal mapX;

    private final BigDecimal mapY;

    private final String tel;

    private final String homepage;

    private final String reserveUrl;

    private final String reserveApp;

    private final String operDate;

    private final String sbrsCl;

    private final String posblFcltyCl;

    private final String thema;

    private final String animalCmg;


    public CampsiteAddRequest(Long owner_id,String name, String line_intro, String intro, String induty, String addr, String addr1, BigDecimal mapX, BigDecimal mapY, String tel, String homepage, String reserveUrl, String reserveApp, String operDate, String sbrsCl, String posblFcltyCl, String thema, String animalCmg) {
        this.owner_id = owner_id;
        this.name = name;
        this.line_intro = line_intro;
        this.intro = intro;
        this.induty = induty;
        this.addr = addr;
        this.addr1 = addr1;
        this.mapX = mapX;
        this.mapY = mapY;
        this.tel = tel;
        this.homepage = homepage;
        this.reserveUrl = reserveUrl;
        this.reserveApp = reserveApp;
        this.operDate = operDate;
        this.sbrsCl = sbrsCl;
        this.posblFcltyCl = posblFcltyCl;
        this.thema = thema;
        this.animalCmg = animalCmg;
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

    public String getInduty() {
        return induty;
    }

    public String getAddr() {
        return addr;
    }

    public String getAddr1() {return addr1;}

    public BigDecimal getMapX() {
        return mapX;
    }

    public BigDecimal getMapY() {
        return mapY;
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

}
