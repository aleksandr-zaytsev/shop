package ru.azaytsev.catalogue.service;

import ru.azaytsev.catalogue.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAllProducts(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    public void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
