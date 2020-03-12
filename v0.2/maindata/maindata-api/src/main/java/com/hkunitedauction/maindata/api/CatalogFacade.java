package com.hkunitedauction.maindata.api;

import com.hkunitedauction.maindata.model.Catalog;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/catalog")
public interface CatalogFacade {

    @GetMapping
    Catalog query();

    @PutMapping()
    void update(@RequestBody Catalog model);
}
