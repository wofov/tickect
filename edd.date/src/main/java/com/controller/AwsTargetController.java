package com.air_force.controller;

import com.air_force.constants.WebConstants;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Aws 로드밸런서 target 확인")
@RestController
public class AwsTargetController {


    @GetMapping("/")
    public ResponseEntity<String> AwsTarget(){
        return ResponseEntity.ok(WebConstants.OK);
    }
}
