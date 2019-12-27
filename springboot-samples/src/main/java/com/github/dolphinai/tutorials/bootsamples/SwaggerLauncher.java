package com.github.dolphinai.tutorials.bootsamples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
@Slf4j
public class SwaggerLauncher implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		log.info("## Swagger Started");
	}

	@Bean
	public Docket swagger() {
		// .apis(RequestHandlerSelectors.any())
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("System APIs")
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.github.dolphinai.tutorials"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger API Reference")
			.description("Welcome to swagger API reference")
			.termsOfServiceUrl("https://github.com/dolphinai/awesome-tutorials")
			.version("1.0")
			.build();
	}

}
