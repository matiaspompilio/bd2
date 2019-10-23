package info.unlp.edu.ar.bithub.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "commit")
public class Commit {

    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private String message;

    private String hash;

    private ObjectId author;

    private List<File> files;


    public Commit(){}

    public Commit(String message, String hash, ObjectId author, List<File> files){
        this.message = message;
        this.hash = hash;
        this.author = author;
        this.files = files;
    }

    public Commit(String message, String hash){
        this.message = message;
        this.hash = hash;
        this.files= Collections.<File>emptyList();
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

    public ObjectId getAuthor() {
        return author;
    }

    public void setAuthor(ObjectId author) {
        this.author = author;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

}
