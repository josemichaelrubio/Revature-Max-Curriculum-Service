package dev.revaturemax.repositories;

import dev.revaturemax.models.BatchDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchDayRepository extends JpaRepository<BatchDay, Long> {

    @Query("SELECT cd FROM BatchDay cd WHERE cd.batchId = :batchId AND cd.date = :date")
    Optional<BatchDay> findBatchDayByBatchIdAndDate(@Param("batchId") long batchId, @Param("date") LocalDate date);

    /* The following two queries must be called sequentially in the same session to load both quizzes and topics
     * collections of each CurriculumDay. Trying to fetch both at once will result in Hibernate throwing
     * MultipleBagFetchException. Calling them each when not wrapped in the same session will not cache the results
     * of the first. */
    @Query("SELECT DISTINCT cd FROM BatchDay cd LEFT JOIN FETCH cd.quiz WHERE cd.batchId = :batchId")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    List<BatchDay> findByBatchId(long batchId);

    @Query("SELECT DISTINCT cd FROM BatchDay cd LEFT JOIN FETCH cd.topics t LEFT JOIN FETCH t.tech WHERE cd IN :days")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    List<BatchDay> findBatchDayTopics(List<BatchDay> days);

}
