package ru.top.java212.service;

import org.springframework.stereotype.Service;
import ru.top.java212.dao.CategoryRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.model.Category;
import ru.top.java212.model.Product;

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
        product.setCategories(category);
        product.setBusy(false);
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean delete(Integer id) {

        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
