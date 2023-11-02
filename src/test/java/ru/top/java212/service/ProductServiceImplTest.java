package ru.top.java212.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.top.java212.dao.CategoryRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.model.Address;
import ru.top.java212.model.Category;
import ru.top.java212.model.Product;
import ru.top.java212.model.UserInfo;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;



    @Test
    void test_save_product() {
        when(productRepository.save(any())).thenReturn(new Product());
        when(categoryRepository.save(any())).thenReturn(new Category());
        Category category = new Category();
        when(categoryRepository.findByTitle(any())).thenReturn(category);

        Product product = new Product();
        product.setCategories(new Category());
        assertTrue(productServiceImpl.saveProduct(product));
        verify(productRepository).save(any());
        verify(categoryRepository).save(any());
        verify(categoryRepository).findByTitle(any());
        assertSame(category, product.getCategories());
        assertFalse(product.isBusy());
    }


    @Test
    void test_save_product_2() {
        when(productRepository.save(any())).thenReturn(new Product());
        when(categoryRepository.save(any())).thenReturn(new Category());
        when(categoryRepository.findByTitle(any())).thenReturn(null);

        Product product = new Product();
        Category category = new Category();
        product.setCategories(category);
        assertTrue(productServiceImpl.saveProduct(product));
        verify(productRepository).save(any());
        verify(categoryRepository).save(any());
        verify(categoryRepository).findByTitle(any());
        assertSame(category, product.getCategories());
        assertFalse(product.isBusy());
    }


    @Test
    void test_delete() {
        doNothing().when(productRepository).deleteById(any());
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        assertTrue(productServiceImpl.delete(1));
        verify(productRepository).findById(any());
        verify(productRepository).deleteById(any());
    }


    @Test
    void test_delete2() {
        doNothing().when(productRepository).deleteById(any());
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertFalse(productServiceImpl.delete(1));
        verify(productRepository).findById(any());
    }


    @Test
    void test_get_products_by_category() {
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(new ArrayList<>());
        assertTrue(productServiceImpl.getProductsByCategory("Dr").isEmpty());
        verify(productRepository).findByIsBusy(anyBoolean());
    }





    @Test
    void test_get_products_by_category_2() {
        Product product = new Product();
        product.setCategories(new Category(1, "Dr"));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByCategory("Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_category_3() {
        Product product = new Product();
        product.setCategories(new Category(1, "Mr"));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertTrue(productServiceImpl.getProductsByCategory("Dr").isEmpty());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_last_products_by_is_busy() {
        when(productRepository.findFirst10ByOrderByIdDesc()).thenReturn(new ArrayList<>());
        assertTrue(productServiceImpl.getLastProductsByIsBusy(true).isEmpty());
        verify(productRepository).findFirst10ByOrderByIdDesc();
    }


    @Test
    void test_get_last_products_by_is_busy_2() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findFirst10ByOrderByIdDesc()).thenReturn(productList);
        assertTrue(productServiceImpl.getLastProductsByIsBusy(true).isEmpty());
        verify(productRepository).findFirst10ByOrderByIdDesc();
    }


    @Test
    void test_get_last_products_by_is_busy_3() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findFirst10ByOrderByIdDesc()).thenReturn(productList);
        assertTrue(productServiceImpl.getLastProductsByIsBusy(true).isEmpty());
        verify(productRepository).findFirst10ByOrderByIdDesc();
    }



    @Test
    void test_get_last_products_by_is_busy_4() {
        Product product = mock(Product.class);
        when(product.isBusy()).thenReturn(true);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findFirst10ByOrderByIdDesc()).thenReturn(productList);
        assertEquals(1, productServiceImpl.getLastProductsByIsBusy(true).size());
        verify(productRepository).findFirst10ByOrderByIdDesc();
        verify(product).isBusy();
    }


    @Test
    void test_get_products_by_title_not_busy() {
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(new ArrayList<>());
        assertEquals(1, productServiceImpl.getProductsByTitleNotBusy(true, "Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_title_not_busy_2() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByTitleNotBusy(true, "Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_title_not_busy_3() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByTitleNotBusy(true, "Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_title_not_busy_4() {
        ArrayList<Product> productList = new ArrayList<>();
        Category category = new Category();
        productList.add(new Product(category, "Dr", "Nothing found", "Nothing found", BigDecimal.valueOf(42L)));
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByTitleNotBusy(true, "Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }



    @Test
    void test_get_products_by_title_not_busy_5() {
        ArrayList<Product> productList = new ArrayList<>();
        Category category = new Category();
        productList.add(new Product(category, "Mr", "Nothing found", "Nothing found", BigDecimal.valueOf(42L)));
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByTitleNotBusy(true, "Dr").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_address() {
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(new ArrayList<>());
        assertEquals(1, productServiceImpl.getProductsByAddress("District").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }





    @Test
    void test_get_products_by_address_2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(new Address(1, "District", "District", 10, 10));

        Product product = new Product();
        product.setUserInfo(userInfo);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByAddress("District").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }


    @Test
    void test_get_products_by_address_3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(new Address(1, "District", "42", 10, 10));

        Product product = new Product();
        product.setUserInfo(userInfo);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByIsBusy(anyBoolean())).thenReturn(productList);
        assertEquals(1, productServiceImpl.getProductsByAddress("District").size());
        verify(productRepository).findByIsBusy(anyBoolean());
    }
}

