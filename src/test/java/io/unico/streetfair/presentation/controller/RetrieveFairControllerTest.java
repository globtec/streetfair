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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@DisplayName("[GET] /fairs/{registry}")
class RetrieveFairControllerTest {

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
    @DisplayName("Should return status ok")
    @Sql(scripts = "classpath:sql/fair-3048-1.sql")
    void shouldReturnStatusOk() throws Exception {
        var request = get("/fairs/{registry}", "3048-1");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return status not found")
    void shouldReturnStatusNotFound() throws Exception {
        var request = get("/fairs/{registry}", "0000-0");

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isNotFound());
    }
}