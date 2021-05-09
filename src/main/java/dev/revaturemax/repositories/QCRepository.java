package dev.revaturemax.repositories;

import dev.revaturemax.models.QC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QCRepository extends JpaRepository<QC,Long> {

    @Query("select q from QC q left join fetch q.techs where q.id in :ids")
    List<QC> findAllById(Set<Long> ids);
}
