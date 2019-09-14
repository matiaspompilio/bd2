package info.unlp.edu.ar.bithub.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "commit")
public class Commit {

    private String message;

    private String hash;

    @DBRef
    private User author;

    @DBRef
    private List<File> files;


    public Commit(){}

    public Commit(String message, String hash, User author, List<File> files){
        this.message = message;
        this.hash = hash;
        this.author = author;
        this.files = files;
    }

    public Commit(String message, String hash){
        this.message = message;
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String description) {
        this.message = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

}
