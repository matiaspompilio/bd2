package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CommitRepository;
import info.unlp.edu.ar.bithub.repositories.FileRepository.MongoFileRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

@Named
public class FileService {

    @Autowired
    private MongoFileRepository mongoFileRepository;

    @Autowired
    private CommitRepository commitRepository;

    private RestHighLevelClient client;

    public FileService(RestHighLevelClient client){
        this.client=client;
    }

    private MongoFileRepository getMongoFileRepository(){ return this.mongoFileRepository;}

    public List<File> getAllFilesFromMongo(){
        return this.getMongoFileRepository().findAll();
    }
    /*
    public void addFile(ObjectId commit,String content, String filename) throws Exception {
        File file=new File(content,filename);
        Optional<Commit> co =this.commitRepository.findById(commit);
        if(co.isPresent()){
            this.commitRepository.
        } else {
            throw new Exception();
        }
    }
     El método de arriba era para agregar un file a un commit ya existente. Ccomo decidimos pasar
     a armar los test y no armar completa la api de carga de datos esto queda en pausa.
     */
    public List<File> getByContentFromMongo(String content){
        return this.getMongoFileRepository().findByContentRegex(content);
    }

    public File addFile(String content, String filename) {
        File file= new File(content,filename);
        return file;
    }
}
