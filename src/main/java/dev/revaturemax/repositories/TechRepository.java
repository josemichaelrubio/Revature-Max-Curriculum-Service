package dev.revaturemax.repositories;

import dev.revaturemax.models.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {
}
