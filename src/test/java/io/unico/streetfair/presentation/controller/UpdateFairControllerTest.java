package io.unico.streetfair.presentation.controller;

import io.micrometer.core.instrument.util.IOUtils;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@DisplayName("[PUT] /fairs/{registry}")
class UpdateFairControllerTest {

    @Autowired
    private UpdateFairController updateFairController;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(updateFairController)
                .setControllerAdvice(new StreetfairExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should return status ok")
    @Sql(scripts = "classpath:sql/fair-1087-1.sql")
    void shouldReturnStatusOk() throws Exception {
        var request = put("/fairs/{registry}", "1087-1")
                .contentType("application/json")
                .content(getContent("json/fair-1087-1.json"));

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lat").value("-46659953"))
                .andExpect(jsonPath("$.lon").value("-23691790"));
    }

    String getContent(String filename) {
        return IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream(filename));
    }
}