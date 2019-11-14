package info.unlp.edu.ar.bithub.model;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
public class File {

    private String filename;

    private String content;


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

}
