package ru.top.java212.service;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.top.java212.dao.CategoryRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.model.Category;
import ru.top.java212.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public boolean saveProduct(Product product) {
        Category category = product.getCategory();
        Category categoryFromDb = categoryRepository.findByTitle(category.getTitle());

        if (categoryFromDb != null) {
            category = categoryFromDb;
        }
        categoryRepository.save(category);
        product.setCategories(category);
        product.setBusy(false);
       if(productRepository.save(product) == null){
           return false;
       }
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
        productRepository.findByIsBusy(isBusy).iterator().forEachRemaining(result::add);
        List<Product> products = result.stream().filter(product -> StringUtils.containsIgnoreCase(product.getTitle(), title))
                .collect(Collectors.toList());

        if (!products.isEmpty()) {
            return products;
        }

        products.add(new Product(new Category(), "Nothing found", "/img/not found.jpg", "Nothing was found for your query", new BigDecimal(0)));
        return products;
    }

    @Override
    public List<Product> getProductsByAddress(String district) {
        List<Product> result = productRepository.findByIsBusy(false).stream().filter(
                product -> product.getUserInfo().getAddress().getDistrict().equals(district)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result;
        }
        result.add(new Product(new Category(), "Nothing found", "/img/not found.jpg", "Nothing was found for your query", new BigDecimal(0)));
        return result;
    }
}