package com.stupid.community.controller;

import com.stupid.common.api.area.vo.CommunityVO;
import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.community.service.ICommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CommunityController {

    @Autowired
    private ICommunityService communityService;

    /**
     * 保存社区信息
     */
    @PostMapping("save")
    public ResponseObject<String> saveCommunity(@RequestBody CommunityVO communityVO){
        String resp = communityService.saveCommunity(communityVO);
        return new ResponseObject<>(resp);
    }

    /**
     * 查询社区详情
     */
    @GetMapping("info/{id}")
    public ResponseObject<CommunityVO> queryCommunityInfo(@PathVariable Long id){
        CommunityVO resp = communityService.queryCommunityInfo(id);
        return new ResponseObject(resp);
    }
}
