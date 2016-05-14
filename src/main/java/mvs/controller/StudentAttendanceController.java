package mvs.controller;

import mvs.model.*;
import mvs.repository.StudentAttendanceRepository;
import mvs.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang Minh on 4/25/2016.
 */
@RestController
public class StudentAttendanceController  {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentAttendanceRepository studentAttendanceRepository;
    @RequestMapping(path = "/attendances/{id}/addListStudent", method = RequestMethod.POST)
    ResponseEntity<?> addTeacher(
            @PathVariable(value = "id") long id,
            @RequestBody List<Long> listID

    ){

        Attendance studentAttendance = studentAttendanceRepository.findOne(id);
        if(studentAttendance==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            List<Student> list=new ArrayList<Student>();
            for(long s:listID){
                Student student=studentRepository.findOne(s);
                student.getStudentAttendances().add(studentAttendance);
                list.add(student);
                studentRepository.save(student);
            }
            studentAttendance.setStudents(list);
            studentAttendanceRepository.save(studentAttendance);
            return ResponseEntity.status(HttpStatus.OK).body(null);


        }

    }
    @RequestMapping(path = "/attendances/{id}/students", method = RequestMethod.GET)
    ResponseEntity<?> getStudents(
            @PathVariable(value = "id") long id

    ){
        List<Student> students=studentAttendanceRepository.findOne(id).getStudents();
        Response response=new Response();
        if(students==null){
            response.setSuccess(0);
        }
        else{
            response.setSuccess(1);
            response.setResponse(students);
        }
        return ResponseEntity.ok(response);
    }
}
