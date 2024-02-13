package com.campro.campsite.domain;

import com.campro.campsite.presentation.request.CampsiteAddRequest;

import java.math.BigDecimal;

public record Campsite (Long id, Long owner_id ,String name,String line_intro,String intro,String feature,String induty,
                        String addr, String addr1, BigDecimal mapX,BigDecimal mapY,String tel,String homepage,String reserveUrl,
                        String reserveApp,Long gnrlSite,Long autoSite, Long glampSite, Long caravSite,
                        String operPeriod,String operDate,String sbrsCl,String posblFcltyCl,String thema,String animalCmg){


    public static Campsite of(Long owner_id, String name, String line_intro, String intro, String feature, String induty, String addr, String addr1, BigDecimal mapX, BigDecimal mapY, String tel, String homepage, String reserveUrl, String reserveApp, Long gnrlSite, Long autoSite, Long glampSite, Long caravSite, String operPeriod, String operDate, String sbrsCl, String posblFcltyCl, String thema, String animalCmg) {
        return new Campsite(null,owner_id ,name, line_intro, intro, feature, induty, addr, addr1 ,mapX, mapY, tel, homepage, reserveUrl, reserveApp, gnrlSite, autoSite, glampSite, caravSite, operPeriod, operDate, sbrsCl, posblFcltyCl, thema, animalCmg);
    }


    public static Campsite addCampsite(CampsiteAddRequest request){
        return new Campsite(null,request.getOwner_id(),request.getName(),
                request.getLine_intro(),
                request.getIntro(),
                "",
                request.getInduty(),
                request.getAddr(),
                request.getAddr1(),
                request.getMapX(),
                request.getMapY(),
                request.getTel(),
                request.getHomepage(),
                request.getReserveUrl(),
                request.getReserveApp(),
                0L,
                0L,
                0L,
                0L,
                request.getOperDate(),
                request.getOperDate(),
                request.getSbrsCl(),
                request.getPosblFcltyCl(),
                request.getThema(),
                request.getAnimalCmg());
    }

}
