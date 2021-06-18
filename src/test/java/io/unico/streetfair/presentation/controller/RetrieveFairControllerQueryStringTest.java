package io.unico.streetfair.presentation.controller;

import io.unico.streetfair.presentation.errorhandling.StreetfairExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@DisplayName("[GET] /fairs{?district,region5,name,neighborhood}")
@Sql(scripts = "classpath:sql/fair-1001-4_1002-2_1003-0_1004-9_1005-7.sql")
class RetrieveFairControllerQueryStringTest {

    @Autowired
    private RetrieveFairController retrieveFairController;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(retrieveFairController)
                .setControllerAdvice(new StreetfairExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should return status ok and all fairs")
    void shouldReturnStatusOkAndAllFais() throws Exception {
        var request = get("/fairs");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isNotEmpty());
    }

    @Test
    @DisplayName("Should return status ok and only fairs of the Lapa districty")
    void shouldReturnStatusOkAndOnlyFairsOfTheLapaDistrict() throws Exception {
        var request = get("/fairs?district=lapa");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isNotEmpty());
    }

    @Test
    @DisplayName("Should return status ok and only fairs of the lest region5")
    void shouldReturnStatusOkAndOnlyFairsOfTheLestRegion5() throws Exception {
        var request = get("/fairs?region5=leste");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isNotEmpty());
    }

    @Test
    @DisplayName("Should return status ok and only fairs of the concordia name")
    void shouldReturnStatusOkAndOnlyFairsOfTheConcordiaName() throws Exception {
        var request = get("/fairs?name=concordia");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isNotEmpty());
    }

    @Test
    @DisplayName("Should return status ok and only fairs of the Cambuci neighborhood")
    void shouldReturnStatusOkAndOnlyFairsOfTheCambuciNeighborhood() throws Exception {
        var request = get("/fairs?neighborhood=cambuci");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isNotEmpty());
    }

    @Test
    @DisplayName("Should return status ok and no fair")
    void shouldReturnStatusOkAndNoFair() throws Exception {
        var request = get("/fairs?name=unnamed");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fairs").isEmpty());
    }
}