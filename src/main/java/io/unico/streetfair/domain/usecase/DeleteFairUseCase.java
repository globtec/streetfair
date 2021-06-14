package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.repository.FairRepository;

public class DeleteFairUseCase {

    private final FairRepository fairRepository;

    public DeleteFairUseCase(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    public void delete(String registry) {
        fairRepository.deleteById(registry);
    }

}
