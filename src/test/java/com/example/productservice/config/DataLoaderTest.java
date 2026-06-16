package com.example.productservice.config;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void seedProductsSavesThreeSampleProducts() throws Exception {
        DataLoader dataLoader = new DataLoader();
        CommandLineRunner runner = dataLoader.seedProducts(productRepository);

        runner.run();

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(3)).save(captor.capture());

        List<Product> saved = captor.getAllValues();
        assertThat(saved).extracting(Product::getName)
                .containsExactly("Laptop", "Mouse", "Keyboard");
        assertThat(saved).extracting(Product::getPrice)
                .containsExactly(1200.00, 25.50, 45.00);
    }
}
