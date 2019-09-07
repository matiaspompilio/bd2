package info.unlp.edu.ar.bithub.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "file")
public class File {

    private String filename;

    private String content;

    private Commit commit;

    @DBRef
    private List<FileReview> fileReviews;

    public File(){}

    public File(String content, String filename){
        this.filename = filename;
        this.content = content;
    }

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
