package com.jwp.uts.uts_rayhanfebriansyah.Controller;

import com.jwp.uts.uts_rayhanfebriansyah.Model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private int autoId = 1;

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public Object addProduct(@RequestBody Product request) {
        if (request.getName() == null || request.getName().isEmpty()
                || request.getCategory() == null || request.getCategory().isEmpty()
                || request.getPrice() == null) {
            return "Semua field harus diisi";
        }

        Product newP = new Product(autoId++, request.getName(), request.getCategory(), request.getPrice());
        products.add(newP);
        return newP;
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable Integer id) {
    return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .map(p -> (Object) p)          
            .orElse("Product not found");  
    }

}
