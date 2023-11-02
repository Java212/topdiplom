package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.top.java212.model.*;


import java.math.BigDecimal;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void test_save_find_delete_product() {
        Category category = categoryRepository.save(new  Category(1, "categoryTitle"));
        Product product = productRepository.save(new Product(category, "productTitle", "link.ru", "productSpecification", new BigDecimal(123.0)));
        Product actualProduct = productRepository.findByTitle("productTitle");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isGreaterThan(0);
        assertThat(product).isEqualTo(actualProduct);

        productRepository.deleteById(product.getId());
        assertThat(productRepository.findByTitle("productTitle")).isNull();

    }


}
