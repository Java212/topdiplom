package ru.top.java212.service;


import ru.top.java212.model.Product;

public interface ProductService {
    boolean saveProduct(Product product);
    boolean delete(Integer id);
}
