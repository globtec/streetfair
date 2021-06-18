package io.unico.streetfair.configuration;

import io.unico.streetfair.domain.repository.FairRepository;
import io.unico.streetfair.domain.usecase.UpdateFairUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateFairUseCaseConfiguration {

    private final FairRepository fairRepository;

    @Autowired
    public UpdateFairUseCaseConfiguration(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    @Bean
    public UpdateFairUseCase getUpdateFairUseCase() {
        return new UpdateFairUseCase(fairRepository);
    }

}
