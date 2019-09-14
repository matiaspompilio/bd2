package info.unlp.edu.ar.bithub.controllers.CommitController;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.services.CommitService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/mongo/commit")
public class CommitMongoController {
    @Inject
    private CommitService commitService;

    private CommitService getCommitService(){
        return this.commitService;
    }

    @GetMapping
    public List<Commit> getAllCommits() {
        return this.getCommitService().getAllCommitsFromMongo();
    }

    @PostMapping
    public ResponseEntity<?> addCommit(@RequestParam(value = "message")String message,
                                       @RequestParam(value = "hash")String hash,
                                       @RequestParam(value = "author") ObjectId author) {
        ResponseEntity<?> response = null;
        try {
            this.getCommitService().addCommit(message, hash, author, null);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el usuario");
        }
        return response;
    }
}
