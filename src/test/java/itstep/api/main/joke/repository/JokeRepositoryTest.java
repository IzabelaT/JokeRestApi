package itstep.api.main.joke.repository;

import itstep.api.main.joke.entity.Joke;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class JokeRepositoryTest {

    @Autowired
    private JokeRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }




    @Test
    void IfJokeIdDoesNotExist() {
        long id = 2L;

        boolean expected = underTest.findById(id).isPresent();

        assertThat(expected).isFalse();
    }

    @Test
    @Disabled
    void IfJokeIdExist() {
        long id = 1L;
        Joke joke = new Joke(
                1L,
                "Test Jokes",
                "Test"
        );
        underTest.save(joke);

        boolean expected = underTest.findById(id).isPresent();

        assertThat(expected).isTrue();

    }
    @Test
    void IfJokeTextExist() {
        String text = "Test Jokes";
        Joke joke = new Joke(
                1L,
                text,
                "Test"
        );
        underTest.save(joke);
        boolean expected = underTest.findByText(text).isPresent();

        assertThat(expected).isTrue();
    }

    @Test
    void IfJokeTextDoesNotExist() {
        String text = "Test Jokes";

        boolean notExist = underTest.findByText(text).isPresent();

        assertThat(notExist).isFalse();
    }

    @Test
    void IfJokeCategoryExist() {
        String category = "Test";
        Joke joke = new Joke(
                1L,
                "Test Jokes",
                "Test"
        );

        underTest.save(joke);

        boolean expected = underTest.findByCategory(category).isPresent();

        assertThat(expected).isTrue();
    }

    @Test
    void IfJokeCategoryDoesNotExist() {
        String text = "Test";

        boolean notExist = underTest.findByCategory(text).isPresent();

        assertThat(notExist).isFalse();
    }
}