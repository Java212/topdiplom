package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.top.java212.model.Address;
import ru.top.java212.model.Category;
import ru.top.java212.model.Product;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    private List<Category> categories = new ArrayList<>();
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;




    @Test
    void test_save_find_delete_product() {
        Address address = entityManager.persist(new Address("District", "street", 1, 2));
        Category category = entityManager.persist(new Category("Пневмоинструмент"));
        categories.add(category);
        Product product = productRepository.save(new Product(categories, address, "productTitle", "productSpecification", new BigDecimal(123.0)));
        Product actualProduct = productRepository.findByTitle("productTitle");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isGreaterThan(0);
        assertThat(product).isEqualTo(actualProduct);

        productRepository.deleteById(product.getId());
        assertThat(productRepository.findByTitle("productTitle")).isNull();

    }


}
