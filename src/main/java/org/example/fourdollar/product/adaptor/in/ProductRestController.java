package org.example.fourdollar.product.adaptor.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
class ProductRestController {


    @GetMapping
    String getProducts() {
        return "products";
    }

}
