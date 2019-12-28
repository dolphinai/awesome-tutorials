package com.github.dolphinai.tutorials.eurekaclient;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping
    @ResponseBody
    public String hello(@RequestParam("name") String name) {
        System.out.println("Call " + client);
        return "Hello " + name;
    }

}
