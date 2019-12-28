package com.github.dolphinai.tutorials.bootsamples.openfeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stores", contextId="tmpFeign", configuration = FooConfiguration.class)
public class FooClient {



}
