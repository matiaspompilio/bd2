package info.unlp.edu.ar.bithub.controllers.BranchController;

import info.unlp.edu.ar.bithub.model.Branch;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@Document(collection = "branch")
public class BranchMongoController {

    @Inject
    private BranchService branchService;

    private BranchService getUserService(){
        return this.branchService;
    }

    @GetMapping
    public List<Branch> getAllUsers() {
        return this.getBranchService().getAllBranchesFromMongo();
    }

    @PostMapping
    public ResponseEntity<?> addBranch(@RequestParam(value = "name")String name,
                                     @RequestParam(value = "email")String email) {
        ResponseEntity<?> response = null;
        try {
            this.getUserService().addBranch(name, email);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el usuario");
        }
        return response;
    }
}
