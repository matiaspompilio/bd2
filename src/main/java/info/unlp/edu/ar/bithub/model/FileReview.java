package info.unlp.edu.ar.bithub.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileReview")
public class FileReview {

    private Review review;

    private File file;

    private int lineNumber;

    private String comment;

    public FileReview() {}

    public FileReview(Review review, File file, int lineNumber, String comment){
        this.review = review;
        this.file = file;
        this.lineNumber = lineNumber;
        this.comment = comment;
    }


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
