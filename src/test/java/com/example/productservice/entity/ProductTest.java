package com.example.productservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void noArgsConstructorCreatesEmptyProduct() {
        Product product = new Product();

        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getPrice()).isZero();
    }

    @Test
    void allArgsConstructorSetsNameAndPrice() {
        Product product = new Product("Laptop", 1200.00);

        assertThat(product.getName()).isEqualTo("Laptop");
        assertThat(product.getPrice()).isEqualTo(1200.00);
    }

    @Test
    void settersAndGettersWork() {
        Product product = new Product();

        product.setId(5L);
        product.setName("Mouse");
        product.setPrice(25.50);

        assertThat(product.getId()).isEqualTo(5L);
        assertThat(product.getName()).isEqualTo("Mouse");
        assertThat(product.getPrice()).isEqualTo(25.50);
    }
}
