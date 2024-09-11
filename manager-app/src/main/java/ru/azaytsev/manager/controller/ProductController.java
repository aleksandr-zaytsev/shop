package ru.azaytsev.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.azaytsev.manager.controller.payload.UpdateProductPayload;
import ru.azaytsev.manager.entity.Product;
import ru.azaytsev.manager.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalogue/products/{productId:\\d+}"))
public class ProductController {

    private final ProductService productService;


    @ModelAttribute("product")
    public Product product(@PathVariable ("productId") int productId ) {
        return this.productService.findProduct(productId).orElseThrow();
    }
    @GetMapping()
    public String getProduct() {
return "/catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "/catalogue/products/edit";
    }

    @PostMapping("edit")
public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload payload) {
this.productService.updateProduct(product.getId(), payload.title(), payload.details());
return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }
}