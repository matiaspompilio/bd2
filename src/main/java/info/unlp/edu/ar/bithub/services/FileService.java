package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CommitRepository;
import info.unlp.edu.ar.bithub.repositories.FileRepository.FileRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private CommitRepository commitRepository;

    private RestHighLevelClient client;

    public FileService(RestHighLevelClient client){
        this.client=client;
    }

    private FileRepository getFileRepository(){ return this.fileRepository;}

    public List<File> getAllFilesFromMongo(){
        return this.getFileRepository().findAll();
    }

    public void addFile(ObjectId commit,String content, String filename) throws Exception {
        File file=new File(content,filename);
        Optional<Commit> co =this.commitRepository.findById(commit);
        if(co.isPresent()){
            Commit commitAux= co.get();
            commitAux.getFiles().add(file);
            this.commitRepository.save(commitAux);
        } else {
            throw new Exception();
        }
    }
}
