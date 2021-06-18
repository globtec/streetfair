package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairConflictException;
import io.unico.streetfair.domain.repository.FairRepository;

public class CreateFairUseCase {

    private final FairRepository fairRepository;

    public CreateFairUseCase(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    public Fair create(Fair fair) throws FairConflictException {
        if (fairRepository.existsByRegistryCustomQuery(fair.getRegistry())) {
            throw new FairConflictException(String.format(
                    "The fair %s cannot be saved because it already exists", fair.getRegistry()
            ));
        }

        return fairRepository.save(fair);
    }

}
