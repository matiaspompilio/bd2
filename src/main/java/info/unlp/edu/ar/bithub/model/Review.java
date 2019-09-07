package info.unlp.edu.ar.bithub.model;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "review")
public class Review {

    @DBRef
    private User author;

    @DBRef
    private List<FileReview> fileReviews;

    public Review() {}

    public Review(Branch branch, User author){
        this.author = author;
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
