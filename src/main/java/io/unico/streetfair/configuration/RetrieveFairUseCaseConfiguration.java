package io.unico.streetfair.configuration;

import io.unico.streetfair.domain.repository.FairRepository;
import io.unico.streetfair.domain.usecase.RetrieveFairUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrieveFairUseCaseConfiguration {

    private final FairRepository fairRepository;

    @Autowired
    public RetrieveFairUseCaseConfiguration(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    @Bean
    public RetrieveFairUseCase getRetrieveFairUseCase() {
        return new RetrieveFairUseCase(fairRepository);
    }

}
