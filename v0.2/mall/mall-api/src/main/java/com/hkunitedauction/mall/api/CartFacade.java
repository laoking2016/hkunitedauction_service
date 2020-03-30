package com.hkunitedauction.mall.api;

import com.hkunitedauction.mall.model.Cart;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cart")
public interface CartFacade {

    @GetMapping
    Cart query(@RequestParam("currentUser") String currentUser);

    @PostMapping
    void create(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId);

    @DeleteMapping
    void delete(@RequestParam("currentUser") String currentUser, @RequestParam("ids") Long[] ids);

    @PutMapping
    void update(@RequestParam("currentUser") String currentUser, @RequestParam("goodId") Long goodId, @RequestParam("quantity") Integer quantity);
}
