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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
@DisplayName("[DELETE] /fairs/{registry}")
class DeleteFairControllerTest {

    @Autowired
    private DeleteFairController deleteFairController;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(deleteFairController)
                .setControllerAdvice(new StreetfairExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should return status no content")
    @Sql(scripts = "classpath:sql/fair-4003-7.sql")
    void shouldReturnStatusNoContent() throws Exception {
        var request = delete("/fairs/{registry}", "4003-7");

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }
}