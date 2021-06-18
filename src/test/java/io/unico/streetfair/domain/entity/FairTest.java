package io.unico.streetfair.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("Build a fair entity")
class FairTest {

    @Test
    @DisplayName("Should throw an exception when building a fair that registry code is null")
    void shouldThrowExceptionWhenRegistryCodeIsNull() {
        var thrown = assertThrows(Exception.class, () -> {
            new Fair.Builder(null).build();
        });

        assertThat(thrown.getMessage()).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw an exception when building a fair that registry code is empty")
    void shouldThrowExceptionWhenRegistryCodeIsEmpty() {
        var thrown = assertThrows(Exception.class, () -> {
            new Fair.Builder("").build();
        });

        assertThat(thrown.getMessage()).isNotEmpty();
    }

    @Test
    @DisplayName("Should build a fair entity when a registry code is set")
    void shouldBuildFairWhenRegistryCodeIsSet() throws Exception {
        var registry = "4041-0";
        var fair = new Fair.Builder(registry).build();

        assertThat(fair).isInstanceOf(Fair.class);
        assertThat(fair.getRegistry()).isEqualTo(registry);
    }
}