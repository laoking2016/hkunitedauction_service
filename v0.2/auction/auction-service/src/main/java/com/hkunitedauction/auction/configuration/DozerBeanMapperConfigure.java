package com.hkunitedauction.auction.configuration;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.auction.model.LotPO;
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
                mapping(LotPO.class, Lot.class).exclude("images").exclude("status").exclude("payments");
            }
        };
    }
}
