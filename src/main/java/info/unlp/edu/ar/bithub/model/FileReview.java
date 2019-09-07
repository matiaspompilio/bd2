package info.unlp.edu.ar.bithub.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileReview")
public class FileReview {

    @Id
    private ObjectId _id;

    private Review review;

    private File file;

    private int lineNumber;

    private String comment;

    public FileReview() {}

    public FileReview(ObjectId _id, Review review, File file, int lineNumber, String comment){
        this._id = _id;
        this.review = review;
        this.file = file;
        this.lineNumber = lineNumber;
        this.comment = comment;
    }

    public ObjectId get_id() { return this._id; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public File getReviewedFile(){
        return file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getLineNumber() {
        return new Integer(lineNumber);
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
