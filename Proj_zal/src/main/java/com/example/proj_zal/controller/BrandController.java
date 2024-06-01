package com.example.proj_zal.controller;

import com.example.proj_zal.dto.ErrorDTO;
import com.example.proj_zal.dto.BrandDTO;
import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.mapper.BrandMapper;
import com.example.proj_zal.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


    @RestController
    @RequestMapping("/brand")
    public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BrandDTO>>brands(){

        List<Brand> brands = this.brandService.get();

        return ResponseEntity.ok(BrandMapper.mapBrands(brands));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")

    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long id){
        Brand brand = this.brandService.get(id);

        if(brand == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(BrandMapper.map(brand));
    }


   @PostMapping(consumes = "application/json")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object>store(@RequestBody BrandDTO dto){

        if(dto.getId() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Id cannot be set"));
        }

        Brand brand = BrandMapper.map(dto);
        this.brandService.add(brand);
       // Http status - 201 - Created
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }

   @DeleteMapping("/{index}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<Void>delete(@PathVariable Long id){
        this.brandService.delete(id);
        return ResponseEntity.noContent().build();
   }

   @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BrandDTO dto) {
       Brand existingBrand = this.brandService.get(id);
       if (existingBrand == null) {
           return ResponseEntity.notFound().build();
       }
       existingBrand.setName(dto.getName());
       this.brandService.add(existingBrand);
       return ResponseEntity.ok().build();
   }


}
