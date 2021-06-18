package io.unico.streetfair.configuration;

import io.unico.streetfair.domain.repository.FairRepository;
import io.unico.streetfair.domain.usecase.CreateFairUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateFairUseCaseConfiguration {

    private final FairRepository fairRepository;

    @Autowired
    public CreateFairUseCaseConfiguration(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    @Bean
    public CreateFairUseCase getCreateFairUseCase() {
        return new CreateFairUseCase(fairRepository);
    }

}
