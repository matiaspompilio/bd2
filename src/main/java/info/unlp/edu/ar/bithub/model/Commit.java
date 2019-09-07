package info.unlp.edu.ar.bithub.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "commit")
public class Commit {

    @Id
    private ObjectId _id;

    private String message;

    private String hash;

    private User author;

    @DBRef
    private List<File> files;

    private Branch branch;

    public Commit(){}

    public Commit(ObjectId _id, String message, String hash, User author, List<File> files, Branch branch){
        this._id = _id;
        this.message = message;
        this.hash = hash;
        this.author = author;
        this.files = files;
        this.branch= branch;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }


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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
