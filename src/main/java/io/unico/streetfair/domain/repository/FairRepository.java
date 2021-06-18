package io.unico.streetfair.domain.repository;

import io.unico.streetfair.domain.entity.Fair;

import javax.transaction.Transactional;
import java.util.List;

public interface FairRepository {

    Fair save(Fair fair);

    @Transactional
    void deleteByRegistry(String registry);

    boolean existsByRegistryCustomQuery(String registry);

    Fair findByRegistry(String registry);

    List<Fair> findBy(String district, String region5, String name, String neighborhood);

}
