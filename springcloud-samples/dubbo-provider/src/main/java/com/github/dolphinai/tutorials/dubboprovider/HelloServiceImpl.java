package com.github.dolphinai.tutorials.dubboprovider;

import com.github.dolphinai.tutorials.dubbointerface.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        String result ="Hello " + name;

        return result;
    }
}
