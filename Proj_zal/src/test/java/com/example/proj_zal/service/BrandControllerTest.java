package com.example.proj_zal.service;

import com.example.proj_zal.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @AfterEach
    void clean() {
        brandRepository.deleteAll();
    }

    @Test
    @WithMockUser //pozytywny integracyjny
    void shouldReturnAllBrandsForRoleUser() throws Exception {
        mockMvc.perform(get("/brand"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"}) //pozytywny integracyjny
    void shouldReturnAllBrandsForRoleAdmin() throws Exception {
        mockMvc.perform(get("/brand"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"}) //pozytywny integracyjny
    void shouldCreateNewBrandForAdmin() throws Exception {
        long countBefore = brandRepository.count();

        mockMvc.perform(post("/brand")
                        .content("{\"name\":\"Saab\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        long countAfter = brandRepository.count();

        Assertions.assertEquals(countBefore + 1, countAfter);
    }

    @Test
    @WithMockUser //negatywny integracyjny
    void shouldntCreateNewBrandForUser() throws Exception {
        long countBefore = brandRepository.count();

        mockMvc.perform(post("/brand")
                        .content("{\"name\":\"Saab\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden());

        long countAfter = brandRepository.count();

        Assertions.assertEquals(countBefore, countAfter);
    }

}
