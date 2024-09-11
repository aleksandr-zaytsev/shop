package ru.azaytsev.manager.service;

import ru.azaytsev.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

   public void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
