package io.unico.streetfair.domain.repository;

import io.unico.streetfair.domain.entity.Fair;

import java.util.List;

public interface FairRepository {

    Fair save(Fair fair);

    void deleteById(String registry);

    boolean exists(Fair fair);

    Fair find(String registry);

    List<Fair> findBy(String district, String region5, String fairName, String neighborhood);

}
