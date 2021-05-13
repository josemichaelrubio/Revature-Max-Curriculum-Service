package dev.revaturemax.repositories;

import dev.revaturemax.models.Topic;
import dev.revaturemax.projections.TopicDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select new dev.revaturemax.projections.TopicDTO(t.id, t.tech.name) from Topic t where t.id in :topicIds")
    public List<TopicDTO> findAllById(Set<Long> topicIds);

    public List<Topic> findAllByTechId(Long techId);
}
