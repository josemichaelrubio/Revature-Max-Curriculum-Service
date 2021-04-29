package dev.revaturemax.repositories;

import dev.revaturemax.models.BatchDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDayRepository extends JpaRepository<BatchDay, Long> {
}
