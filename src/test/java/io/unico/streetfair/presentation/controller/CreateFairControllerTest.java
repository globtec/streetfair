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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@DisplayName("[POST] /fairs")
class CreateFairControllerTest {

    @Autowired
    private CreateFairController createFairController;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(createFairController)
                .setControllerAdvice(new StreetfairExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should return status created")
    void shouldReturnStatusCreated() throws Exception {
        var request = post("/fairs")
                .contentType("application/json")
                .content(getContent("json/fair-4041-1.json"));

        mvc.perform(request)
                .andExpect(header().stringValues("Content-Type", "application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return status bad request")
    void shouldReturnStatusBadRequest() throws Exception {
        var request = post("/fairs")
                .contentType("application/json");

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return status conflict")
    @Sql(scripts = "classpath:sql/fair-4045-2.sql")
    void shouldReturnStatusConflict() throws Exception {
        var request = post("/fairs")
                .contentType("application/json")
                .content(getContent("json/fair-4045-2.json"));

        mvc.perform(request)
                .andExpect(status().isConflict());
    }

    String getContent(String filename) {
        return IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream(filename));
    }
}