package com.hkunitedauction.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages="com.hkunitedauction.mall.mapper")
@SpringBootApplication
//@EnableEurekaClient
@EnableSwagger2
public class MallApplication {

	/*@Bean
	public FilterRegistrationBean corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}
}
