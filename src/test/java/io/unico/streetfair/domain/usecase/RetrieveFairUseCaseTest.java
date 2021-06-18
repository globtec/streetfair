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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use case to retrieve fair")
class RetrieveFairUseCaseTest {

    @Mock
    FairRepository fairRepository;

    RetrieveFairUseCase retrieveFairUseCase;

    @BeforeEach
    void setUp() {
        retrieveFairUseCase = new RetrieveFairUseCase(fairRepository);
    }

    @Test
    @DisplayName("Should retrieve fair by registry code when the fair exists")
    void shouldRetrieveByRegistry() throws FairNotFoundException {
        var registry = "registry";
        var fair = mock(Fair.class);

        when(fairRepository.findByRegistry(registry)).thenReturn(fair);

        var fairFound = retrieveFairUseCase.find(registry);

        verify(fairRepository).findByRegistry(registry);

        assertThat(fairFound).isEqualTo(fair);
    }

    @Test
    @DisplayName("Should throw an exception when retrieving a fair by registry code that was not found")
    void shouldThrowFairNotFoundExceptionWhenFairNotExists() {
        assertThrows(FairNotFoundException.class, () -> {
            var registry = "registry";

            when(fairRepository.findByRegistry(registry)).thenReturn(null);

            retrieveFairUseCase.find(registry);

            verify(fairRepository).findByRegistry(registry);
        });
    }

    @Test
    @DisplayName("Should retrieve fairs by parameters and return a list of the fairs")
    void shouldSearchByParameters() {
        var district = "district";
        var region5 = "region5";
        var name = "name";
        var neighborhood = "neighborhood";

        var fair1 = mock(Fair.class);
        var fair2 = mock(Fair.class);

        var fairs = List.of(fair1, fair2);

        when(fairRepository.findBy(district, region5, name, neighborhood)).thenReturn(fairs);

        var fairsFound = retrieveFairUseCase.findBy(district, region5, name, neighborhood);

        verify(fairRepository).findBy(district, region5, name, neighborhood);

        assertThat(fairsFound).isEqualTo(fairs);
    }

}