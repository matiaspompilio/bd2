package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.File;
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

    private RestHighLevelClient client;

    public FileService(RestHighLevelClient client){
        this.client=client;
    }

    private FileRepository getFileRepository(){ return this.fileRepository;}

    public Optional<File> getAllFilesFromMongo(ObjectId id){
        return this.getFileRepository().findById(id);
    }

    public void addFile(String content, String filename){
        File file=new File(content,filename);
        this.getFileRepository().save(file);
    }
}
