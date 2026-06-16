package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void savesAndFindsAllProducts() {
        productRepository.save(new Product("Keyboard", 45.00));
        productRepository.save(new Product("Monitor", 300.00));

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getName)
                .containsExactlyInAnyOrder("Keyboard", "Monitor");
        assertThat(products).allSatisfy(p -> assertThat(p.getId()).isNotNull());
    }

    @Test
    void findAllReturnsEmptyWhenNoProductsPersisted() {
        assertThat(productRepository.findAll()).isEmpty();
    }
}
