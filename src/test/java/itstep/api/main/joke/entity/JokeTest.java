package itstep.api.main.joke.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class JokeTest {
    Joke joke;
    @BeforeEach
    void setUp() {
        joke = new Joke(
                1L,
                "123",
                "12333"
        );

    }
    @Test
    void isNotSameAsJokeAnotherObject() {
        Joke joke1 = new Joke(
                "123",
                "12333"
        );
        assertThat(joke).isNotSameAs(joke1);
    }
    @Test
    void isExactlyInstanceOfJoke() {
        assertThat(joke).isExactlyInstanceOf(Joke.class);
    }
    @Test
    void isInstanceOfJoke() {
        assertThat(joke).isInstanceOf(Joke.class);
    }
    @Test
    void isNotNullObject() {
        assertThat(joke).isNotNull();
    }
    @Test
    void isNullObject() {
        Joke joke1 = new Joke();
        assertThat(joke1.getId()).isNull();
        assertThat(joke1.getText()).isNull();
        assertThat(joke1.getCategory()).isNull();
    }
    @Test
    void canGetId() {
        long id = 2L;
        Joke joke1 = new Joke(
                2L,
                "123",
                "12333"
        );
        assertThat(joke1.getId()).isEqualTo(id);
    }
    @Test
    void cannotGetId() {
        long id = 3L;
        Joke joke1 = new Joke(
                2L,
                "123",
                "12333"
        );
        assertThat(joke1.getId()).isNotEqualTo(id);
    }
    @Test
    void canSetId() {
        long id = 3L;
        Joke joke1 = new Joke(
                id,
                "123",
                "12333"
        );
        joke1.setId(2L);
        assertThat(joke1.getId()).isEqualTo(2L);
    }

    @Test
    void canGetText() {
        String text = "123";
        Joke joke1 = new Joke(
                2L,
                "123",
                "12333"
        );
        assertThat(joke1.getText()).isEqualTo(text);
    }
    @Test
    void cannotGetText() {
        String text = "12344";
        Joke joke1 = new Joke(
                2L,
                "123",
                "12333"
        );
        assertThat(joke1.getText()).isNotEqualTo(text);
    }

    @Test
    void canSetText() {
        String text = "1234";
        Joke joke1 = new Joke(
                2L,
                "123",
                "12333"
        );
        joke1.setText(text);
        assertThat(joke1.getText()).isEqualTo(text);
    }

    @Test
    void canGetCategory() {
        String category = "1234";
        Joke joke1 = new Joke(
                2L,
                "123",
                "1234"
        );
        assertThat(joke1.getCategory()).isEqualTo(category);
    }
    @Test
    void cannotGetCategory() {
        String category = "1235";
        Joke joke1 = new Joke(
                2L,
                "123",
                "1234"
        );
        assertThat(joke1.getCategory()).isNotEqualTo(category);
    }
    @Test
    void canSetCategory() {
        String category = "1234";
        Joke joke1 = new Joke(
                2L,
                "123",
                "123334"
        );
        joke1.setCategory(category);
        assertThat(joke1.getCategory()).isEqualTo(category);
    }

    @Test
    void testToString() {
        Joke joke1 = new Joke(
                1L,
                "123",
                "12333"
        );

        assertThat(joke.toString()).isEqualTo(joke1.toString());
    }

}