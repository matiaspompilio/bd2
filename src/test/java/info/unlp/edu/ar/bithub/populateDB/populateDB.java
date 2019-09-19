package info.unlp.edu.ar.bithub.populateDB;

import com.github.javafaker.Faker;
import info.unlp.edu.ar.bithub.model.Branch;
import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.services.BranchService;
import info.unlp.edu.ar.bithub.services.CommitService;
import info.unlp.edu.ar.bithub.services.FileService;
import info.unlp.edu.ar.bithub.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class populateDB {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    CommitService commitService;

    @Autowired
    BranchService branchService;

    Faker faker = new Faker();

    @Test
    public void populateWithDataFalopa(){
        Random random = new Random();
        List<Branch> branches = new ArrayList<>();
        for (int i = 0; i < random.nextInt(10); i++) {
            branches.add(branchService.addBranch(faker.funnyName().name()));
        }
        for (int i = 0; i < random.nextInt(100); i++) {
            User user = userService.addUser(faker.name().fullName(),faker.internet().emailAddress());
            for (int j = 0; j < random.nextInt(1000); j++) {
                List<File> files = new ArrayList<>();
                for (int k = 0; k < random.nextInt(100); k++) {
                    files.add(
                        this.fileService.addFile(
                            faker.lorem().characters(10,100),faker.pokemon().name()+" "+faker.demographic().race()+" "+faker.lordOfTheRings().character()
                        )
                    );
                }
                Commit commit = this.commitService.addCommit(faker.harryPotter().quote(),faker.internet().password(),user,files);
                Branch branch = branches.get(random.nextInt(branches.size()));
                branch.addCommit(commit);
                branchService.save(branch);
            }
        }
    }
}
