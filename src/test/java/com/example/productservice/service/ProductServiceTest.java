package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProductsReturnsAllProductsFromRepository() {
        List<Product> products = List.of(
                new Product("Laptop", 1200.00),
                new Product("Mouse", 25.50)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Product::getName).containsExactly("Laptop", "Mouse");
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() {
        when(productRepository.findAll()).thenReturn(List.of());

        List<Product> result = productService.getAllProducts();

        assertThat(result).isEmpty();
        verify(productRepository, times(1)).findAll();
    }
}
