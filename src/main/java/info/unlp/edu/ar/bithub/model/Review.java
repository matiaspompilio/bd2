package info.unlp.edu.ar.bithub.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "review")
public class Review {

    @Id
    private ObjectId _id;

    private Branch branch;

    private User author;

    @DBRef
    private List<FileReview> fileReviews;

    public Review() {}

    public Review(ObjectId _id, Branch branch, User author){
        this._id = _id;
        this.branch = branch;
        this.author = author;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public List<FileReview> getReviews() {
        return fileReviews;
    }

    public List<FileReview> getFileReviews() {
        return fileReviews;
    }

    public void setFileReviews(List<FileReview> fileReviews) {
        this.fileReviews = fileReviews;
    }
}
