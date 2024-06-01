package com.example.proj_zal.mapper;

import com.example.proj_zal.dto.BrandDTO;
import com.example.proj_zal.entity.Brand;

import java.util.ArrayList;
import java.util.List;

public final class BrandMapper {
    public static BrandDTO map(Brand brand){
        BrandDTO dto = new BrandDTO();

        dto.setId(brand.getId());
        dto.setName(brand.getName());

        return dto;
    }

    public static List<BrandDTO> mapBrands(List<Brand> brands){
        List<BrandDTO>dtos = new ArrayList<>();

        for (Brand brand : brands){
            dtos.add(map(brand));
        }
        return dtos;
    }

    public static Brand map(BrandDTO dto){
        Brand brand = new Brand();

        brand.setId(dto.getId());
        brand.setName(dto.getName());

        return brand;
    }

    public static List<Brand>mapDtos(List<BrandDTO>dtos){
        List<Brand> brands = new ArrayList<>();

        for (BrandDTO dto : dtos){
            brands.add(map(dto));
        }
        return brands;
    }
}
