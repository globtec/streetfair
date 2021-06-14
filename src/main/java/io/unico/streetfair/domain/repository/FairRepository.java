package io.unico.streetfair.domain.repository;

import io.unico.streetfair.domain.entity.Fair;

public interface FairRepository {

    Fair save(Fair fair);

    void deleteById(String registry);

    boolean exists(Fair fair);

}