package com.example.proj_zal.service;

import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BrandServiceValidationTest {

    private BrandService brandService;

    @BeforeEach
    void init() {
        BrandRepository brandRepository = Mockito.mock(BrandRepository.class);
        brandService = new BrandService(brandRepository);
    }

    @Test // jednostkowy sprawdzajacy walidacje pojedynczej metody "add"
    void shouldThrowExceptionWhenNameIsEmpty() {
        Brand brand = new Brand();
        brand.setName("");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            brandService.add(brand);
        });

        Assertions.assertEquals("Brand name cannot be empty", exception.getMessage());
    }

    @Test // jednostkowy sprawdzajacy walidacje pojedynczej metody "add"
    void shouldThrowExceptionWhenNameIsNull() {
        Brand brand = new Brand();
        brand.setName(null);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            brandService.add(brand);
        });

        Assertions.assertEquals("Brand name cannot be empty", exception.getMessage());
    }
}
