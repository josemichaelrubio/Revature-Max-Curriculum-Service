package dev.revaturemax.repositories;

import dev.revaturemax.models.QC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QCRepository extends JpaRepository<QC,Long> {





}
