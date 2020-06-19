package com.hkunitedauction.order.configuration;

import com.hkunitedauction.order.model.Order;
import com.hkunitedauction.order.model.OrderDetail;
import com.hkunitedauction.order.model.OrderDetailPO;
import com.hkunitedauction.order.model.OrderPO;
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
                mapping(OrderPO.class, Order.class).exclude("detail").exclude("status").exclude("type");
                mapping(OrderDetailPO.class, OrderDetail.class).exclude("images");
            }
        };
    }
}
