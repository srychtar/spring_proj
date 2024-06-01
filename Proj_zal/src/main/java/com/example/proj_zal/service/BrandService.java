package com.example.proj_zal.service;

import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    //dodawanie danych
    public void add(Brand brand){
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Brand name cannot be empty");
        }
        this.brandRepository.save(brand);
    }

    //pobieranie danych
    public Brand get(Long id){

        Optional<Brand>optionalBrand=this.brandRepository.findById(id);

        return optionalBrand.orElse(null);
    }
    //zwracanie wszystkich danych
    public List<Brand>get(){
        return this.brandRepository.findAll();
    }

    //usuwanie danych
    public void delete(Long id){
        this.brandRepository.deleteById(id);
    }
}
