package ru.top.java212.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.top.java212.model.Address;
import ru.top.java212.model.Category;
import ru.top.java212.model.Product;


import java.math.BigDecimal;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {



    @Autowired
    ProductRepository productRepository;




    @Test
    void test_save_find_delete_product() {


        Product product = productRepository.save(new Product(1,new Category(1,"Пневмоинструмент"), new Address(1,"District", "street", 1, 2),
                "productTitle","link.ru", "productSpecification", new BigDecimal(123.0)));
        Product actualProduct = productRepository.findByTitle("productTitle");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isGreaterThan(0);
        assertThat(product).isEqualTo(actualProduct);

        productRepository.deleteById(product.getId());
        assertThat(productRepository.findByTitle("productTitle")).isNull();

   }


}
