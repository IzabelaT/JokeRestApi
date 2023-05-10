package itstep.api.main.joke.config;

import itstep.api.main.joke.repository.JokeRepository;
import itstep.api.main.joke.entity.Joke;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class JokeConfig {
    @Bean
    CommandLineRunner commandLineRunner(JokeRepository jokeRepository) {
        return args -> {
            Joke joke1 = new Joke(
                    "A SQL statement walks into a bar and sees two tables.\nIt approaches, and asks \"may I join you?\"",
                    "Programming"
            );
            Joke joke2 = new Joke(
                    "I'd tell you a joke about NAT but I would have to translate.",
                    "Programming"
            );
            Joke joke3 = new Joke(
                    "Why was the JavaScript developer sad?, Because they didn't Node how to Express themself!",
                    "Programming"
            );
            Joke joke4 = new Joke(
                    "I'd tell you a joke about NAT but I would have to translate.",
                    "Programming"
            );
            Joke joke5 = new Joke(
                    "Judge: \"I sentence you to the maximum punishment...\"\nMe (thinking): \"Please be death, please be death...\"\nJudge: \"Learn Java!\"\nMe: \"Damn.\"",
                    "Programming"
            );
            Joke joke6 = new Joke(
                    "UDP is better in the COVID era since it avoids unnecessary handshakes.",
                    "Programming"
            );
            Joke joke7 = new Joke(
                    "Genders are a lot like booleans. There's only two of them.",
                    "Programming"
            );
            Joke joke8 = new Joke(
                    "How many programmers does it take to screw in a light bulb? None. It's a hardware problem.",
                    "Programming"
            );
            Joke joke9 = new Joke(
                    "I have a joke about Stack Overflow, but you would say it's a duplicate.",
                    "Programming"
            );
            Joke joke10 = new Joke(
                    "Your mama's so FAT she can't save files bigger than 4GB.",
                    "Programming"
            );
            jokeRepository.saveAll(List.of(joke1,joke2,joke3,joke4,joke5,joke6,joke7,joke8,joke9,joke10));
        };
    }
}
