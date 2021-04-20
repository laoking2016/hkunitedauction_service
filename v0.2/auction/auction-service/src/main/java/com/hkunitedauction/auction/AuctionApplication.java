package com.hkunitedauction.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@MapperScan(basePackages="com.hkunitedauction.auction.mapper")
@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
public class AuctionApplication {

	@PostConstruct
	void started(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

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
		SpringApplication.run(AuctionApplication.class, args);
	}
}
