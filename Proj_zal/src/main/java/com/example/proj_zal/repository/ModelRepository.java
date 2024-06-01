package com.example.proj_zal.repository;

import com.example.proj_zal.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

    Model findByName(String name);
    List<Model> findAllByBrandId(Long id);
}
