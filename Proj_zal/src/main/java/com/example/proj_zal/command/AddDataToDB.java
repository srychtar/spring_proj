package com.example.proj_zal.command;

import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.entity.Model;
import com.example.proj_zal.repository.BrandRepository;
import com.example.proj_zal.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddDataToDB implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Override
    public void run(String... args) throws Exception {

        Brand brand = brandRepository.findByName("Honda");
        if(brand == null){
            brand = new Brand();
            brand.setName("Honda");

            brand = brandRepository.save(brand);

            Model accord = new Model();
            accord.setName("Accord");
            accord.setBrand(brand);

            Model civic = new Model();
            civic.setName("Civic");
            civic.setBrand(brand);

            modelRepository.save(accord);
            modelRepository.save(civic);

        }
    }
}
//dodawanie danych do bd