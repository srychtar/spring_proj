package com.example.proj_zal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandDTO {

    private Long id;

    private String name;
}
