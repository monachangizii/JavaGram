package Model;

import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Post {
    String name;
    String text;
    Blob picture;
    Timestamp time;
    Path path;


    public Post(String name, String text, Blob picture, Timestamp time) {
        this.name = name;
        this.text = text;
        this.picture = picture;
        this.time = time;
    }
    public Post(String name, String text, Blob picture) {
        this.name = name;
        this.text = text;
        this.picture = picture;

    }

    public Post(String name, String text, Path path) {
        this.name = name;
        this.text = text;
        this.path = path;
    }

    public Post(String text, Timestamp time) {
        this.text = text;
        this.time = time;
    }

    public Post(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public Post() {
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


}
