package info.unlp.edu.ar.bithub.controllers.FileController;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.services.FileService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/mongo/file")
public class FileMongoController {
    @Inject
    private FileService fileService;

    private FileService getFileService(){
        return this.fileService;
    }

    @GetMapping
    public List<File> getAllFiles() {
        return this.getFileService().getAllFilesFromMongo();
    }

    /*
    @PostMapping
    public ResponseEntity<?> addFile(@RequestParam(value = "content")String content,
                                     @RequestParam(value = "filename")String filename,
                                     @RequestParam(value = "commit")ObjectId commit
                                     ) {
        ResponseEntity<?> response = null;
        try {
            this.getFileService().addFile(commit,content,filename);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el file");
        }
        return response;
    }
     El método de arriba era para agregar un file a un commit ya existente. Ccomo decidimos pasar
     a armar los test y no armar completa la api de carga de datos esto queda en pausa.
     */

}
