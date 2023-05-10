package itstep.api.main.joke.service;

import itstep.api.main.joke.entity.Joke;
import itstep.api.main.joke.repository.JokeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class JokeService {
    private final JokeRepository jokeRepository;

    @Autowired
    public JokeService(JokeRepository jokeRepository){
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getAllJokes(){ return jokeRepository.findAll(); }

    public Optional<Joke> getJokeById(Long id){
        return jokeRepository.findById(id);
    }
    public Optional<Joke> getJokeByText(String text){ return jokeRepository.findByText(text); }
    public Optional<Joke> getJokeByCategory(String category){
        return jokeRepository.findByCategory(category);
    }
    public Joke addNewJoke(Joke joke){
        boolean exist = jokeRepository.existsById(joke.getId());

        if(exist){
            throw new IllegalStateException("Joke with id [" + joke.getId() + "] exists");
        }
        jokeRepository.save(joke);
        return joke;
    }
    public void deleteJoke(Long id) {
        boolean exists = jokeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Joke with id [" + id + "] doesn't exists");
        }
        jokeRepository.deleteById(id);
    }

    @Transactional
    public Joke updateJoke(Long id,
                            String text,
                            String category) {
        Joke joke = jokeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Joke with id [" + id + "] doesn't exists"));

        if(text != null &&
                text.length() > 0 &&
                !Objects.equals(joke.getText(), text)){
            Optional<Joke> jokeOptional = jokeRepository
                    .findByText(text);
            if(jokeOptional.isPresent()){
                throw new IllegalStateException("Joke is taken");
            }
            joke.setText(text);
        }
        if(category != null &&
                category.length() > 0 &&
                !Objects.equals(joke.getCategory(), category)){
            joke.setCategory(category);
        }
        return joke;

    }

}
