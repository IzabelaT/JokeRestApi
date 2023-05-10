package itstep.api.main.joke.controller;


import itstep.api.main.joke.controller.JokeController;
import itstep.api.main.joke.entity.Joke;
import itstep.api.main.joke.service.JokeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(JokeController.class)
class JokeControllerTest {
    @MockBean
    JokeService jokeService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getJoke() throws Exception {
        List<Joke> jokeList = Arrays.asList(new Joke(
                1L,
                "Test Jokes1",
                "Test1"
        ),
                new Joke(
                        2L,
                        "Test Jokes2",
                        "Test2"
                ));

        when(jokeService.getAllJokes()).thenReturn(jokeList);
        mockMvc.perform(get("/api/v1/jokes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("Test Jokes1"));
    }

    @Test
    void registerNewJoke() throws Exception {
        Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );
        when(jokeService.addNewJoke(joke1)).thenReturn(joke1);
        mockMvc.perform(post("/api/v1/jokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(joke1)))
                .andExpect(status().isOk());
    }
    @Test
    void NotFound() throws Exception {
        Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );

        jokeService.addNewJoke(joke1);
        mockMvc.perform(post("/jokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(joke1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getJokeById() throws Exception {
        Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );
        when(jokeService.getJokeById(1L)).thenReturn(Optional.of(joke1));
        mockMvc.perform(get("/api/v1/jokes/id/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deleteJoke() throws Exception {
        Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );
        doNothing().when(jokeService).deleteJoke(1L);
        mockMvc.perform(delete("/api/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(joke1)))
                .andExpect(status().isOk());
    }

    @Test
    void updateJoke() throws Exception {
        Joke joke1 = new Joke(
                1L,
                "Test Jokes2",
                "Test2"
        );
        when(jokeService.updateJoke(1L,"ss","ss")).thenReturn(joke1);


        ResultActions response = mockMvc.perform(put("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joke1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}