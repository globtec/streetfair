package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.domain.repository.FairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use case to update fair")
class UpdateFairUseCaseTest {

    @Mock
    FairRepository fairRepository;

    @Mock
    Fair fair;

    UpdateFairUseCase updateFairUseCase;

    @BeforeEach
    void setUp() {
        updateFairUseCase = new UpdateFairUseCase(fairRepository);
    }

    @Test
    @DisplayName("Should update the fair when the fair exists")
    void shouldUpdateFairWhenFairExists() throws FairNotFoundException {
        var registry = "registry";

        when(fair.getRegistry()).thenReturn(registry);
        when(fairRepository.existsByRegistryCustomQuery(registry)).thenReturn(true);
        when(fairRepository.save(fair)).thenReturn(fair);

        var fairUpdated = updateFairUseCase.update(fair);

        verify(fairRepository).existsByRegistryCustomQuery(registry);
        verify(fairRepository).save(fair);

        assertThat(fairUpdated).isEqualTo(fair);
    }

    @Test
    @DisplayName("Should throw an exception when updating a fair that does not exist")
    void shouldThrowFairNotFoundExceptionWhenFairNotExists() {
        assertThrows(FairNotFoundException.class, () -> {
            var registry = "registry";

            when(fair.getRegistry()).thenReturn(registry);
            when(fairRepository.existsByRegistryCustomQuery(registry)).thenReturn(false);

            updateFairUseCase.update(fair);

            verify(fairRepository).existsByRegistryCustomQuery(registry);
            verify(fairRepository, never()).save(fair);
        });
    }

}