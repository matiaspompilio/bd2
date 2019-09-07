package info.unlp.edu.ar.bithub.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tag")
public class Tag {


    private String commitHash;

    private String name;

    public Tag(){}

    public Tag(String commitHash, String name){
        this.commitHash=commitHash;
        this.name=name;
    }

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

