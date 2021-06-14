package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.repository.FairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use case to crate fair")
class CreateFairUseCaseTest {

    @Mock
    FairRepository fairRepository;

    @Mock
    Fair fair;

    CreateFairUseCase createFairUseCase;

    @BeforeEach
    void setUp() {
        createFairUseCase = new CreateFairUseCase(fairRepository);
    }

    @Test
    @DisplayName("Should save a fair")
    void shouldSaveFair() {
        when(fairRepository.save(fair)).thenReturn(fair);

        var fairCreated = createFairUseCase.create(fair);

        verify(fairRepository).save(fair);

        assertThat(fair).isEqualTo(fairCreated);
    }
}