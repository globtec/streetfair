package io.unico.streetfair.configuration;

import io.unico.streetfair.domain.repository.FairRepository;
import io.unico.streetfair.domain.usecase.DeleteFairUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteFairUseCaseConfiguration {

    private final FairRepository fairRepository;

    @Autowired
    public DeleteFairUseCaseConfiguration(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    @Bean
    public DeleteFairUseCase getDeleteFairUseCase() {
        return new DeleteFairUseCase(fairRepository);
    }
}
