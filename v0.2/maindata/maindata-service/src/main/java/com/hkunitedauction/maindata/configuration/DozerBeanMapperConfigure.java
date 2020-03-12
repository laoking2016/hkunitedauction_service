package com.hkunitedauction.maindata.configuration;

import com.hkunitedauction.maindata.model.Product;
import com.hkunitedauction.maindata.model.ProductPO;
import com.hkunitedauction.maindata.model.User;
import com.hkunitedauction.maindata.model.UserPO;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerBeanMapperConfigure {

    @Bean
    public Mapper dozerMapper() {
        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingBuilder(beanMappingBuilder())
                .build();
        return mapper;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
                mapping(ProductPO.class, Product.class).exclude("images");
                mapping(UserPO.class, User.class).exclude("role");
            }
        };
    }
}
