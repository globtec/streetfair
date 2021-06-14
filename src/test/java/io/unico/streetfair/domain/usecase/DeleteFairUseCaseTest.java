package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.repository.FairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use case to delete fair")
class DeleteFairUseCaseTest {

    @Mock
    FairRepository fairRepository;

    DeleteFairUseCase deleteFairUseCase;

    @BeforeEach
    void setUp() {
        deleteFairUseCase = new DeleteFairUseCase(fairRepository);
    }

    @Test
    @DisplayName("Should delete a fair through your registry code")
    void shouldDeleteFairByRegistry() {
        var registry = anyString();

        deleteFairUseCase.delete(registry);

        verify(fairRepository).deleteById(registry);
    }

}