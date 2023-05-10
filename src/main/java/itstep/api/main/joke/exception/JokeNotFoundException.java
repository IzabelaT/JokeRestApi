package itstep.api.main.joke.exception;

public class JokeNotFoundException extends RuntimeException{
    public JokeNotFoundException(Long id) { super("Could not found joke with [" + id + "]"); }
}
