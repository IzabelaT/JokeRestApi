package itstep.api.main.joke.service;

import itstep.api.main.joke.entity.Joke;
import itstep.api.main.joke.repository.JokeRepository;
import itstep.api.main.joke.service.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class JokeServiceTest {

    @Mock
    private JokeRepository jokeRepository;

    private JokeService underTest;
    private Joke joke;

    @BeforeEach
    void setUp() {
        underTest = new JokeService(jokeRepository);
        joke = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );

    }

    @Test
    void canGetAllJokes() {

        underTest.getAllJokes();

        verify(jokeRepository).findAll();
    }

    @Test
    void getJokeById() {
       Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );
        underTest.addNewJoke(joke1);

        underTest.getJokeById(joke1.getId());
        verify(jokeRepository).findById(joke1.getId());
    }



    @Test
    void canAddNewJoke() {
        // when
        underTest.addNewJoke(joke);

        //then
        ArgumentCaptor<Joke> jokeArgumentCaptor =
                ArgumentCaptor.forClass(Joke.class);

        verify(jokeRepository)
                .save(jokeArgumentCaptor.capture());

        Joke capturedJoke = jokeArgumentCaptor.getValue();

        assertThat(capturedJoke).isEqualTo(joke);
    }

    @Test
    void willThrowWhenIdIsTaken() {
        given(jokeRepository.existsById(joke.getId()))
                .willReturn(true);

        // when
        assertThatThrownBy(() ->underTest.addNewJoke(joke))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Joke with id [" + joke.getId() + "] exists");

        //then
        verify(jokeRepository, never()).save(any());

    }



    @Test
    void updateJoke() {
        Joke joke1 = new Joke();
        joke1.setId(1L);
        joke1.setText("Update Test");
        joke1.setCategory("Update");


        when(jokeRepository.findById(anyLong())).thenReturn(Optional.of(joke1));


        Joke updateJoke = underTest.updateJoke(joke1.getId(), "Update1",joke1.getCategory());


        assertThat(updateJoke.getText()).isEqualTo("Update1");

    }

}