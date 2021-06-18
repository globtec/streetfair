package io.unico.streetfair.infrastructure.repository;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.repository.FairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Behavior of the fair repository")
class FairRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FairRepository fairRepository;

    @BeforeEach
    void setUp() throws Exception {
        entityManager.persist(new Fair.Builder("0000-1")
                .district("district 1")
                .region5("region")
                .name("name 1")
                .neighborhood("neighborhood 1")
                .build());

        entityManager.persist(new Fair.Builder("0000-2")
                .district("district 2")
                .region5("region")
                .name("name 2")
                .neighborhood("neighborhood 2")
                .build());
    }

    @Test
    @DisplayName("Should return false when there is no fair with the registry code")
    void shouldReturnFalseWhenThereIsNoFairWithTheRegistryCode() {
        var result = fairRepository.existsByRegistryCustomQuery("0000-0");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should return true when there is a fair with the registry code")
    void shouldReturnTrueWhenThereIsFairWithTheRegistryCode() {
        var result = fairRepository.existsByRegistryCustomQuery("0000-1");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Should return all fairs when no parameters have been sent")
    void shouldReturnAllFairsWhenNoParametersHaveBeenSent() {
        var fairs = fairRepository.findBy(null, null, null, null);

        assertThat(fairs.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return an empty list when a parameter that doesn't exist is sent")
    void shouldReturnAnEmptyListWhenTheParameterDoIsNotExistIsSent() {
        var fairs = fairRepository.findBy("nonexistent", null, null, null);

        assertThat(fairs.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return the fairs given the parameters sent")
    void shouldReturnTheFairsGivenTheParametersSent() {
        List<Fair> fairs;

        fairs = fairRepository.findBy(null, "region", null, null);
        assertThat(fairs.size()).isEqualTo(2);

        fairs = fairRepository.findBy("district 1", "region", null, null);
        assertThat(fairs.size()).isEqualTo(1);

        fairs = fairRepository.findBy("district 2", "region", null, null);
        assertThat(fairs.size()).isEqualTo(1);
    }
}