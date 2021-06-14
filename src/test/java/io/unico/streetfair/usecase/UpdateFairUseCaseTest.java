package io.unico.streetfair.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.repository.FairRepository;
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
        when(fairRepository.exists(fair)).thenReturn(true);
        when(fairRepository.save(fair)).thenReturn(fair);

        var fairUpdated = updateFairUseCase.update(fair);

        verify(fairRepository).exists(fair);
        verify(fairRepository).save(fair);

        assertThat(fair).isEqualTo(fairUpdated);
    }

    @Test
    @DisplayName("Should throw an exception when updating a fair that does not exist")
    void shouldThrowFairNotFoundExceptionWhenFairNotExists() {
        assertThrows(FairNotFoundException.class, () -> {
            when(fairRepository.exists(fair)).thenReturn(false);

            var fairUpdated = updateFairUseCase.update(fair);

            verify(fairRepository).exists(fair);
            verify(fairRepository, never()).save(fair);
        });
    }

}