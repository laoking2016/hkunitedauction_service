package com.hkunitedauction.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hkunitedauction.util.LongJsonDeserializer;
import com.hkunitedauction.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value="Cart")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart {

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "buyer")
    private String buyer;

    @ApiModelProperty(value = "cart items")
    private Map<Long, Integer> items;

    public static Cart build(String buyer, Long goodId){

        Cart model = new Cart();
        model.setBuyer(buyer);
        HashMap<Long, Integer> items = new HashMap<>();
        items.put(goodId, 1);
        model.setItems(items);
        return model;
    }

    public void createItem(Long goodId){

        if(this.items.containsKey(goodId)){
            int quantity = this.items.get(goodId);
            quantity++;
            this.items.replace(goodId, quantity);
        }else{
            this.items.put(goodId, 1);
        }
    }

    public void deleteItem(Long[] ids){
        for(Long id : ids){
            if(this.items.containsKey(id)){
                int quantity = this.items.get(id);
                quantity--;
                if(quantity <= 0){
                    this.items.remove(id);
                }else{
                    this.items.replace(id, quantity);
                }
            }
        }
    }

    public void updateItem(Long goodId, Integer quantity){
        if(this.items.containsKey(goodId)){
            this.items.put(goodId, quantity);
        }
    }
}
