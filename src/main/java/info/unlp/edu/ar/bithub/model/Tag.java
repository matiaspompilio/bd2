package info.unlp.edu.ar.bithub.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tag")
public class Tag {

    @Id
    private ObjectId _id;

    private String commitHash;

    private String name;

    public Tag(){}

    public Tag(ObjectId _id, String commitHash, String name){
        this._id = _id;
        this.commitHash=commitHash;
        this.name=name;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

