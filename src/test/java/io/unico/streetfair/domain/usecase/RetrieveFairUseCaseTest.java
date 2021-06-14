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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use case to retrieve fair")
class RetrieveFairUseCaseTest {

    @Mock
    FairRepository fairRepository;

    @Mock
    Fair fair;

    RetrieveFairUseCase retrieveFairUseCase;

    @BeforeEach
    void setUp() {
        retrieveFairUseCase = new RetrieveFairUseCase(fairRepository);
    }

    @Test
    @DisplayName("Should retrieve fair by registry code when the fair exists")
    void shouldRetrieveByRegistry() throws FairNotFoundException {
        var registry = anyString();

        when(fairRepository.find(registry)).thenReturn(fair);

        var fairFound = retrieveFairUseCase.find(registry);

        verify(fairRepository).find(registry);

        assertThat(fair).isEqualTo(fairFound);
    }

    @Test
    @DisplayName("Should throw an exception when retrieving a fair by registry code that was not found")
    void shouldThrowFairNotFoundExceptionWhenFairNotExists() {
        assertThrows(FairNotFoundException.class, () -> {
            var registry = anyString();

            when(fairRepository.find(registry)).thenReturn(null);

            retrieveFairUseCase.find(registry);

            verify(fairRepository).find(registry);
        });
    }

    @Test
    @DisplayName("Should retrieve fairs by parameters and return a list of the fairs")
    void shouldSearchByParameters() {
        var district = anyString();
        var region5 = anyString();
        var fairName = anyString();
        var neighborhood = anyString();
        var fairs = List.of(fair, fair);

        when(fairRepository.findBy(district, region5, fairName, neighborhood)).thenReturn(fairs);

        var fairsFound = retrieveFairUseCase.findBy(district, region5, fairName, neighborhood);

        verify(fairRepository).findBy(district, region5, fairName, neighborhood);

        assertThat(fairs).isEqualTo(fairsFound);
    }

}