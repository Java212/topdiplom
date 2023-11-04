package ru.top.java212.service;

import org.springframework.stereotype.Service;
import ru.top.java212.repository.CategoryRepository;
import ru.top.java212.repository.ProductRepository;
import ru.top.java212.entity.Category;
import ru.top.java212.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }
    @Override
    public boolean saveProduct(Product product) {
        Category category = product.getCategory();
        Category categoryFromDb = categoryRepository.findByTitle(category.getTitle());

        if (categoryFromDb != null) {
            category = categoryFromDb;
        }
        categoryRepository.save(category);
        product.setCategories(category);
        product.setBusy(false);
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getProductsByCategory(String title) {

        List<Product> result = new ArrayList<>();
        productRepository.findByIsBusy(false).iterator().forEachRemaining(result::add);
        return result.stream().filter(product -> product.getCategories().getTitle().equals(title)).collect(Collectors.toList());
    }
    @Override
    public List<Product> getLastProductsByIsBusy(boolean isBusy) {

        List<Product> result = new ArrayList<>();
        productRepository.findFirst10ByOrderByIdDesc().iterator().forEachRemaining(result::add);
        return result.stream().filter(product -> product.isBusy() == isBusy).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByTitleNotBusy(boolean isBusy, String title) {
        List<Product> result = new ArrayList<>();
        List<Product> productsFromDb = new ArrayList<>();
         productsFromDb = productRepository.findAllByTitle(title);
        if (!productsFromDb.isEmpty()) {
            productsFromDb.iterator().forEachRemaining(result::add);
            return result.stream().filter(product -> product.isBusy() == isBusy).collect(Collectors.toList());
        }
        productsFromDb.add(new Product(new Category(),"Not found","null","Nothing was found for your query",new BigDecimal(0)));
        return productsFromDb;
    }
}