package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
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

    @Autowired
    private CommitRepository commitRepository;

    private RestHighLevelClient client;

    public FileService(RestHighLevelClient client){
        this.client=client;
    }

    private FileRepository getFileRepository(){ return this.fileRepository;}

    public List<File> getAllFilesFromMongo(){
        return this.getFileRepository().findAll();
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

    public void addFile(String content, String filename) {
        File file= new File(content,filename);
        this.getFileRepository().save(file);
    }
}
