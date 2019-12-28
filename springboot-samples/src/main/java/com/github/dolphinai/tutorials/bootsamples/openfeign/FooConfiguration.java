package com.github.dolphinai.tutorials.bootsamples.openfeign;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;

public class FooConfiguration {

	@Bean
	public Decoder decoder() {
		return new JacksonDecoder();
	}
}
