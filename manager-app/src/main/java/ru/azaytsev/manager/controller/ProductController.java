package ru.azaytsev.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.azaytsev.manager.client.BadRequestException;
import ru.azaytsev.manager.client.ProductsRestClient;
import ru.azaytsev.manager.controller.payload.UpdateProductPayload;
import ru.azaytsev.manager.entity.Product;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping(("catalogue/products/{productId:\\d+}"))
public class ProductController {

    private final ProductsRestClient productsRestClient;


    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productsRestClient.findProduct(productId).orElseThrow();
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
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product product,
                                UpdateProductPayload payload,
                                Model model) {

        try {
            this.productsRestClient.updateProduct(product.id(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "/catalogue/products/edit";
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productsRestClient.deleteProduct(product.id());
        return "redirect:/catalogue/products/list";
    }
}