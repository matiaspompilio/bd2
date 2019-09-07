package info.unlp.edu.ar.bithub.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="branch")
public class Branch {

    private String name;


    private List<Commit> commits= new ArrayList<Commit>();

    public Branch(){}

    public Branch(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
