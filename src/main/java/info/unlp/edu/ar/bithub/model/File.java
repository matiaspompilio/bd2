package info.unlp.edu.ar.bithub.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "file")
public class File {

    @Id
    private ObjectId _id;

    private String filename;

    private String content;

    private Commit commit;

    @DBRef
    private List<FileReview> fileReviews;

    public File(){}

    public File(ObjectId _id, String content, String filename){
        this._id = _id;
        this.filename = filename;
        this.content = content;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String name) {
        this.filename = name;
    }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }


    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<FileReview> getFileReviews() {
        return fileReviews;
    }

    public void setFileReviews(List<FileReview> fileReviews) {
        this.fileReviews = fileReviews;
    }
}
