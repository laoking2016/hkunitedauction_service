package com.hkunitedauction.maindata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

//@EnableEurekaClient
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan(basePackages="com.hkunitedauction.maindata.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class MaindataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaindataServiceApplication.class, args);
	}
}
