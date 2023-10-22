package ru.top.java212.service;


import ru.top.java212.model.Product;

import java.util.List;

public interface ProductService {
    boolean saveProduct(Product product);
    boolean delete(Integer id);


    List<Product> getProductsByCategory(String title);

    List<Product> getLastProductsByIsBusy(boolean isBusy);

    List<Product> getProductsByTitleNotBusy(boolean isBusy, String title);
}
