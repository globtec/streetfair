package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.domain.repository.FairRepository;

public class UpdateFairUseCase {

    private final FairRepository fairRepository;

    public UpdateFairUseCase(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    public Fair update(Fair fair) throws FairNotFoundException {
        if (fairRepository.exists(fair)) {
            return fairRepository.save(fair);
        }

        throw new FairNotFoundException(String.format(
                "The fair %s cannot be updated because it does not exist", fair.getRegistry()
        ));
    }

}
