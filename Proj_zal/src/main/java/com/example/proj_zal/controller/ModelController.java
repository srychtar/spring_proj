package com.example.proj_zal.controller;

import com.example.proj_zal.dto.ErrorDTO;
import com.example.proj_zal.dto.ModelDTO;
import com.example.proj_zal.entity.Brand;
import com.example.proj_zal.entity.Model;
import com.example.proj_zal.mapper.ModelMapper;
import com.example.proj_zal.service.BrandService;
import com.example.proj_zal.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ModelController {

    private static final Logger log = LoggerFactory.getLogger(ModelController.class);
    private final BrandService brandService;
    private final ModelService modelService;

    @GetMapping("/brand/{id}/model")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ModelDTO>>models(@PathVariable("id")Long brandId){
        Brand brand = brandService.get(brandId);
        if(brand == null){
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        List<Model> models = modelService.getAllByBrandId(brandId);
        List<ModelDTO>dtos = ModelMapper.mapModels(models);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/brand/{brandId}/model/{modelId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ModelDTO>models(
            @PathVariable("brandId") Long brandId,
            @PathVariable("modelId") Long modelId
    ){
        Model model= modelService.get(modelId);
        if (model==null){
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        if(!Objects.equals(model.getBrand().getId(),brandId)){
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ModelMapper.map(model));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "brand/{brandId}/model")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object>store(
            @PathVariable("brandId") Long brandId,
            @RequestBody ModelDTO dto
    ){
        log.info("XDDDDDDDDDDDDDDDDd");

        Brand brand = brandService.get(brandId);
        if(brand == null){
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        if (dto.getId() !=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Id cannot be set"));
        }

        Model model= ModelMapper.map(dto);
        model.setBrand(brand);

        this.modelService.add(model);
        // Http status - 201 - Created
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/brand/{brandId}/model/{modelId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @PathVariable("brandId") Long brandId,
            @PathVariable("modelId") Long modelId
    ) {
        Brand brand = brandService.get(brandId);
        if (brand == null) {
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        Model model = modelService.get(modelId);
        if (model == null) {
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        if (model.getBrand().getId() != brand.getId()) {
            // Http Status - 404 - Not Found
            return ResponseEntity.notFound().build();
        }

        this.modelService.delete(modelId);

        // Http Status - 204 - No Content
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/brand/{brandId}/model/{modelId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> update(
            @PathVariable("brandId") Long brandId,
            @PathVariable("modelId") Long modelId,
            @RequestBody ModelDTO dto
    ) {
        Brand brand = brandService.get(brandId);
        if (brand == null) {
            return ResponseEntity.notFound().build();
        }

        Model existingModel = modelService.get(modelId);
        if (existingModel == null || !Objects.equals(existingModel.getBrand().getId(), brandId)) {
            return ResponseEntity.notFound().build();
        }

        existingModel.setName(dto.getName());
        modelService.add(existingModel);

        return ResponseEntity.ok().build();
    }

}
