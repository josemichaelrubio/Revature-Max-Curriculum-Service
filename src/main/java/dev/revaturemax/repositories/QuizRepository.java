package dev.revaturemax.repositories;

import dev.revaturemax.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query("select q from Quiz q left join fetch q.techs where q.id = :quizId")
	public Quiz getOne(Long quizId);


	@Query("select q from Quiz q left join fetch q.techs where q.id in :ids")
	public List<Quiz> findAllById(Set<Long> ids);
}
