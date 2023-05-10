package itstep.api.main.joke.entity;

import jakarta.persistence.*;

@Table
@Entity
public class Joke {


    @SequenceGenerator(
            name = "joke_sequence",
            sequenceName = "joke_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "joke_sequence"
    )
    @Id
    private Long id;
    private String text;
    private String category;

    public Joke() {
    }

    public Joke(String text, String category) {
        this.text = text;
        this.category = category;
    }

    public Joke(Long id, String text, String category) {
        this.id = id;
        this.text = text;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
