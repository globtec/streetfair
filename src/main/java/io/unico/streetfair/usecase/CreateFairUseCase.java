package io.unico.streetfair.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.repository.FairRepository;

public class CreateFairUseCase {

    private final FairRepository fairRepository;

    public CreateFairUseCase(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    public Fair create(Fair fair) {
        return fairRepository.save(fair);
    }

}
