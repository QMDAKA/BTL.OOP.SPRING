package mvs.controller;

import mvs.model.Classroom;
import mvs.model.Response;
import mvs.model.Teacher;
import mvs.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Quang Minh on 4/15/2016.
 */
@RestController
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @RequestMapping(path="/teachers/check",method = RequestMethod.GET)
    ResponseEntity<?> checkSignIn(
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            @RequestParam(value = "pass", required = false, defaultValue = "") String pass

    ){
        Response response=new Response();
        Teacher teacher=teacherRepository.findByEmail(email);
        if(teacher==null){
            response.setSuccess(0);
            response.setResponse(null);
            return ResponseEntity.ok(response);
        }
        if(teacher.getPass().compareTo(pass)==0){
            response.setSuccess(1);
            response.setResponse(teacher);
        }
        else{
            response.setSuccess(0);
            response.setResponse(null);

        }
        return ResponseEntity.ok(response);

    }
    @RequestMapping(path="/teachers",method = RequestMethod.GET)
    ResponseEntity<?> getAll(

    ){

        return ResponseEntity.ok(teacherRepository.findAll());

    }
    @RequestMapping(path = "/teachers/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getTeacher(
            @PathVariable(value = "id") long id
    ){

        Teacher teacher = teacherRepository.findOne(id);
        if(teacher==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(teacher);

        }
    }
    @RequestMapping(path="/teachers",method = RequestMethod.POST)
    ResponseEntity<?> addTeacher(
            @RequestParam(value="name", required=true,defaultValue = "") String name,
            @RequestParam(value = "email", required = true, defaultValue = "") String email,
            @RequestParam(value = "pass", required = false, defaultValue = "") String pass
    ){
        Teacher teacher=new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPass(pass);
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @RequestMapping(path="/teachers/",method = RequestMethod.POST)
    ResponseEntity<?> addTeacher2(
            @RequestBody String name,
            @RequestBody String email,
            @RequestBody String pass
    ){
        Teacher teacher=new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPass(pass);
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
