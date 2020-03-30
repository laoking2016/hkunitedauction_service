package com.hkunitedauction.mall.configuration;

import com.hkunitedauction.mall.model.Cart;
import com.hkunitedauction.mall.model.CartPO;
import com.hkunitedauction.mall.model.Good;
import com.hkunitedauction.mall.model.GoodPO;
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
                mapping(GoodPO.class, Good.class).exclude("images").exclude("status").exclude("payments");
                mapping(CartPO.class, Cart.class).exclude("items");
            }
        };
    }
}
