package com.example.proj_zal.mapper;

import com.example.proj_zal.dto.ModelDTO;
import com.example.proj_zal.entity.Model;
import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static ModelDTO map(Model model){
        ModelDTO dto = new ModelDTO();

        dto.setId(model.getId());
        dto.setName(model.getName());

        return dto;
    }

    public static List<ModelDTO> mapModels(List<Model> models){
        List<ModelDTO>dtos = new ArrayList<>();

        for (Model model : models){
            dtos.add(map(model));

        }
        return dtos;
    }

    public static Model map(ModelDTO dto){
        Model model = new Model();

        model.setId(dto.getId());
        model.setName(dto.getName());

        return model;
    }

    public static List<Model>mapDtos(List<ModelDTO>dtos){
        List<Model> models = new ArrayList<>();

        for (ModelDTO dto : dtos){
            models.add(map(dto));
        }
        return models;
    }
}
