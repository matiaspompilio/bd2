package info.unlp.edu.ar.bithub.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="branch")
public class Branch {

    @Id
    private ObjectId _id;

    private String name;

    private List<Review> reviews= new ArrayList<Review>();

    private List<Commit> commits= new ArrayList<Commit>();

    public Branch(){}

    public Branch(ObjectId _id, String name){
        this._id = _id;
        this.name = name;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
