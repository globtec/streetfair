package io.unico.streetfair.domain.usecase;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import io.unico.streetfair.domain.repository.FairRepository;

import java.util.List;

public class RetrieveFairUseCase {

    private final FairRepository fairRepository;

    public RetrieveFairUseCase(FairRepository fairRepository) {
        this.fairRepository = fairRepository;
    }

    public Fair find(String registry) throws FairNotFoundException {
        Fair fair = fairRepository.find(registry);

        if (null == fair) {
            throw new FairNotFoundException(String.format(
               "The fair %s is not found", registry
            ));
        }

        return fair;
    }

    public List<Fair> findBy(String district,
                             String region5,
                             String fairName,
                             String neighborhood) {
        return fairRepository.findBy(district, region5, fairName, neighborhood);
    }

}
