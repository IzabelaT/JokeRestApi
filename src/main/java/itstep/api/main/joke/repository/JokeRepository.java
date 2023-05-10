package itstep.api.main.joke.repository;

import itstep.api.main.joke.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    Optional<Joke> findById(Long id);

    Optional<Joke> findByText(String text);

    Optional<Joke> findByCategory(String category);
}