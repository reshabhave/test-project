package com.example.productservice.config;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("Laptop", 1200.00));
            productRepository.save(new Product("Mouse", 25.50));
            productRepository.save(new Product("Keyboard", 45.00));
        };
    }
}
