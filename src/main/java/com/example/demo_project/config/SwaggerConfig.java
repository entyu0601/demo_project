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
//@Configuration // �� Spring �Ӹ��J�����O�]�w
//@EnableSwagger2 // �ҥ� Swagger2.createRestApi ��ƫإ�Docket��Bean
//public class SwaggerConfig {
//	
//	
//	/*	
//	 * �إ�API ���򥻸�T(�o�ǰ򥻸�T�|��ܦb��󭶭�) 
//	 * �y�X���}: http:// �M�׹��IP:port/Swagger-ui.html
//	 */
//	public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//			.title("Restful ")
//			.build();
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(DEFAULT_API_INFO) // ���api�򥻸�T
//				.select() // �^�Ǥ@�� ApiSelectorBuilder ��ҡA�Ψӱ���Ǥ����i�H��Swagger�Ӯi�{
//				// �]�w�M�󱽴y���|
//				// Swagger�����y�M��U�Ҧ���crontroller �w�q��API�ò��ͤ��
//				// �Y���QAPI���ͬ������A�i�bAPI�W�[�W@ApiIgnore
//				.apis(RequestHandlerSelectors.basePackage("com.example.demo_project.controller"))
//				.paths(PathSelectors.any()).build();
//	}
//
//}
