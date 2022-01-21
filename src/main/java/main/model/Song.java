package main.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Song{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[a-zA-Z0-9]{1,800}$")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9]{1,300}$")
    private String author;
    @Pattern(regexp = "^[a-zA-Z0-9]{1,1000}$")
    private String song;

    @ManyToOne
    private Genre genre;

    public Song() {
    }

    public Song(int id, String name, String author, String song, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.song = song;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
