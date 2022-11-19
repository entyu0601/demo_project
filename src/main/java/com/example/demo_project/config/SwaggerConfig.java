//package com.example.demo_project.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.annotations.Hidden;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.builders.ApiInfoBuilder;
//
//@Configuration // 讓 Spring 來載入該類別設定
//@EnableSwagger2 // 啟用 Swagger2.createRestApi 函數建立Docket的Bean
//public class SwaggerConfig {
//	
//	
//	/*	
//	 * 建立API 的基本資訊(這些基本資訊會顯示在文件頁面) 
//	 * 造訪網址: http:// 專案實際IP:port/Swagger-ui.html
//	 */
//	public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//			.title("Restful ")
//			.build();
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(DEFAULT_API_INFO) // 顯示api基本資訊
//				.select() // 回傳一個 ApiSelectorBuilder 實例，用來控制那些介面可以給Swagger來展現
//				// 設定套件掃描路徑
//				// Swagger曾掃描套件下所有的crontroller 定義的API並產生文件
//				// 若不想API產生相關文件，可在API上加上@ApiIgnore
//				.apis(RequestHandlerSelectors.basePackage("com.example.demo_project.controller"))
//				.paths(PathSelectors.any()).build();
//	}
//
//}
