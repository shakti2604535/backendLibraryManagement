package com.library.manage.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger Configuration Employee API")
				.description("This is the documentation   for Swagger Configuration Sample API service.").license("")
				.licenseUrl("http://www.abc.org").termsOfServiceUrl("").version("1.0.0").build();
	}

	/*
	 * @Bean public Docket customImplementation(){ return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.basePackage(
	 * "com.example.swagger.swaggerexample.controller")) .build()
	 * .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
	 * .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
	 * .apiInfo(apiInfo()); }
	 */
	
	// advanced implementation
	@Bean
	public Docket customImplementation() {
		final List<ResponseMessage> globalResponseMessageList = Arrays.asList(
				new ResponseMessageBuilder().code(200).message("OK").build(),
				new ResponseMessageBuilder().code(400).message(
						"The server cannot or will not process the request due to something that is perceived to be a client error eg. malformed request syntax,")
						.build(),
				new ResponseMessageBuilder().code(401).message("You are not authorized to view the resource").build(),
				new ResponseMessageBuilder().code(403)
						.message("Accessing the resource you were trying to reach is forbidden").build(),
				new ResponseMessageBuilder().code(404).message("The resource you were trying to reach is not found")
						.build(),
				new ResponseMessageBuilder().code(500)
						.message("Internal server error : Internal exceptions which cannot handle eg. Database down")
						.build());

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.library.manage.controller")).build()
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
				.globalResponseMessage(RequestMethod.GET, globalResponseMessageList)
				.globalResponseMessage(RequestMethod.POST, globalResponseMessageList)
				.globalResponseMessage(RequestMethod.DELETE, globalResponseMessageList)
				.globalResponseMessage(RequestMethod.PUT, globalResponseMessageList)
				.globalResponseMessage(RequestMethod.PATCH, globalResponseMessageList).apiInfo(apiInfo());
	}
}

