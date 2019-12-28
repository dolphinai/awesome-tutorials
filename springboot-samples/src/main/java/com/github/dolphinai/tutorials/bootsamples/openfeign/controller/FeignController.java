package com.github.dolphinai.tutorials.bootsamples.openfeign.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Auth API")
@RestController
@RequestMapping("/feign")
public class FeignController {

}
