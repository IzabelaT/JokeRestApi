package itstep.api.main.joke.controller;

import itstep.api.main.joke.service.JokeService;
import itstep.api.main.joke.entity.Joke;
import itstep.api.main.joke.exception.JokeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class JokeController {
    private JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }


    @GetMapping(path = "/jokes")
    public List<Joke> getJoke(){ return jokeService.getAllJokes(); }
    @PostMapping(path = "/jokes")
    public void registerNewJoke(@RequestBody Joke joke) { jokeService.addNewJoke(joke); }

    @GetMapping("/jokes/id/{id}")
    public Optional<Joke> getJokeById(@PathVariable Long id){
        return Optional.ofNullable(jokeService.getJokeById(id).orElseThrow(() -> new JokeNotFoundException(id)));
    }
    @DeleteMapping(path = "{id}")
    public void deleteJoke(@PathVariable("id") Long id){
        jokeService.deleteJoke(id);
    }

    @PutMapping(path = "{id}")
    public Joke updateJoke(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String category){
        jokeService.updateJoke(id, text, category);
        return jokeService.updateJoke(id, text, category);
    }

}
