package com.campro.campsite.presentation.controller;


import com.amazonaws.Response;
import com.campro.campsite.application.*;
import com.campro.campsite.application.DTO.CampsiteFindDTO;
import com.campro.campsite.infrastructure.entity.CampsiteEntity;
import com.campro.campsite.presentation.request.CampsiteImgRequest;
import com.campro.campsite.presentation.request.CampsiteAddRequest;
import com.campro.campsite.presentation.response.CampsiteFindResponse;
import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campsite")
public class CampsiteController {

    private final CampsiteService campsiteService;
    private final CampsiteKeywordService campsiteKeywordService;
    private final CampsiteImgService campsiteImgService;

    public CampsiteController(CampsiteService campsiteService, CampsiteKeywordService campsiteKeywordService, CampsiteImgService campsiteImgService) {
        this.campsiteService = campsiteService;
        this.campsiteKeywordService = campsiteKeywordService;
        this.campsiteImgService = campsiteImgService;
    }


    /**
     *
     * @param addr
     * @param reserve
     * @param person
     * @return
     */
    @Operation(summary = "캠핑장 검색", description = "캠핑장 검색하기")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CampsiteFindDTO>>> searchCampsite(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("addr") String addr, @RequestParam(value = "reserve",required = false) String reserve, @RequestParam(value = "person",required = false) String person){
        List<CampsiteEntity> campsiteList = campsiteService.searchCampsite(addr,reserve,person,pageable);
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.GET_INFO_SUCCESS.getCode(), ResponseCode.GET_INFO_SUCCESS.getMessage(),CampsiteFindDTO.toDTOList(campsiteList)));

    }

    @Operation(summary = "캠핑장 등록", description = "캠핑장 등록하기")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addCampSite(@RequestPart("info") CampsiteAddRequest request, @RequestPart("keyword") List<String> keywordList, @RequestPart(value = "image", required = false) List<CampsiteImgRequest> imageList) {
        CampsiteEntity entity = campsiteService.addCampsite(request, imageList.get(0).getUploadUrl());
        campsiteKeywordService.addKeyword(entity.getId(), keywordList);
        campsiteImgService.addImage(entity.getId(), imageList);

        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.POST_CREATE_SUCCESS.getCode(), ResponseCode.POST_CREATE_SUCCESS.getMessage(), "OK"));

    }


    @Operation(summary = "캠핑장 수정", description = "캠핑장 수정하기")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateCampsite(@PathVariable Long id, @RequestPart("info") CampsiteAddRequest request, @RequestPart(value = "image", required = false) List<CampsiteImgRequest> imageList, @RequestPart(value = "removeImg", required = false) List<CampsiteImgRequest> removeImgList) {
        System.out.println("request = " + request.getName());
        campsiteService.updateCampsite(id,request);
        campsiteImgService.delImage(id,removeImgList);
        campsiteImgService.addImage(id,imageList);

        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.PATCH_SUCCESS.getCode(), ResponseCode.PATCH_SUCCESS.getMessage(), "OK"));
    }

    @Operation(summary = "캠핑장 삭제", description = "캠핑장 삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delCampSite(@PathVariable Long id) {
        campsiteService.delCampsite(id);
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.DELTE_SUCCESS.getCode(), ResponseCode.DELTE_SUCCESS.getMessage(), "OK"));
    }


    @Operation(summary = "캠핑장 조회", description = "캠핑장 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CampsiteFindResponse>> getCampsite(@PathVariable Long id){
        CampsiteEntity entity = campsiteService.findCampsite(id);
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.GET_INFO_SUCCESS.getCode(), ResponseCode.GET_INFO_SUCCESS.getMessage(), CampsiteFindResponse.from(CampsiteFindDTO.toDTO(entity))));
    }
}
