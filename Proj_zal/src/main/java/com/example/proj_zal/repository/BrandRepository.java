package com.example.proj_zal.repository;

import com.example.proj_zal.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Brand findByName(String name);

}
