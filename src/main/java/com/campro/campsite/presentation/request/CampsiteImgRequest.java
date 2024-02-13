package com.campro.campsite.presentation.request;

public class CampsiteImgRequest {

    private final String originName;
    private final String uploadUrl;

    public CampsiteImgRequest(String originName, String uploadUrl) {
        this.originName = originName;
        this.uploadUrl = uploadUrl;
    }

    public String getOriginName() {
        return originName;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }
}
