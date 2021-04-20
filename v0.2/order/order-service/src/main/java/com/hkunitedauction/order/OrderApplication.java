package com.hkunitedauction.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages="com.hkunitedauction.order.mapper")
@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class OrderApplication {

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
		SpringApplication.run(OrderApplication.class, args);
	}
}
