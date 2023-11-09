package com.aral.manager;

import com.aral.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userprofile-elastic-search", url = "${myapplication.feignclient.elasticsearch}", decode404 = true)
public interface IUserProfileElasticService {

    @PostMapping("/save")
    ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto);
}
