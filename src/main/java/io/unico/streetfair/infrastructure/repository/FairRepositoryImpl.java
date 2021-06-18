package io.unico.streetfair.infrastructure.repository;

import io.unico.streetfair.domain.entity.Fair;
import io.unico.streetfair.domain.repository.FairRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FairRepositoryImpl extends FairRepository, JpaRepository<Fair, String> {

    @Query("select case when count(f) > 0 then true else false end from Fair f where f.registry = :registry")
    boolean existsByRegistryCustomQuery(@Param("registry") String registry);

    @Query("select f from Fair f where (f.district = :district or :district is null) and (f.region5 = :region5 or :region5 is null) and (f.name = :name or :name is null) and (neighborhood = :neighborhood or :neighborhood is null)")
    List<Fair> findBy(@Param("district") String district,
                      @Param("region5") String region5,
                      @Param("name") String name,
                      @Param("neighborhood") String neighborhood);

}
