package com.example.proj_zal.service;

import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


public class BrandServiceTest {
    private BrandRepository brandRepository;
    private BrandService brandService;

    @BeforeEach
    void init() {
        brandRepository = Mockito.mock(BrandRepository.class);
        brandService = new BrandService(brandRepository);
    }

    @Test // pozytywny jednostkowy
    void shouldAddResourceToRepository() {
        Mockito.when(brandRepository.save(any()))
                .thenAnswer(invocationOnMock -> {
                    Brand brand = invocationOnMock.getArgument(0, Brand.class);
                    brand.setId(1L);

                    return brand;
                });

        Brand brand = new Brand();
        brand.setName("Honda");

        brandService.add(brand);

        Mockito.verify(brandRepository).save(any());
        Assertions.assertEquals(1L, brand.getId());
        Assertions.assertEquals("Honda", brand.getName());
    }

    @Test
    void shouldFindBrand() { //pozytywny jednostkowy
        Mockito.when(brandRepository.findById(any()))
                .thenAnswer(invocationOnMock -> {
                    Long id = invocationOnMock.getArgument(0, Long.class);

                    Brand brand = new Brand();
                    brand.setId(id);
                    brand.setName("Honda");

                    return Optional.of(brand);
                });

        Brand returnedBrand = brandService.get(1L);
        Mockito.verify(brandRepository).findById(any());
        Assertions.assertEquals(1L, returnedBrand.getId());
        Assertions.assertEquals("Honda", returnedBrand.getName());
    }

    @Test
    void shouldntFindBrand() { //negatywny jednostkowy
        Mockito.when(brandRepository.findById(any())).thenReturn(Optional.empty());

        Brand returnedBrand = brandService.get(1L);
        Mockito.verify(brandRepository).findById(any());
        Assertions.assertNull(returnedBrand);
    }

}
