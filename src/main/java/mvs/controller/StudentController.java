package mvs.controller;

import mvs.model.Classroom;
import mvs.model.Mark;
import mvs.model.Student;
import mvs.model.Attendance;
import mvs.repository.MarkRepository;
import mvs.repository.StudentAttendanceRepository;
import mvs.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Quang Minh on 4/17/2016.
 */
@RestController
public class StudentController {
    @Autowired
    public static  StudentRepository studentRepository;
    @Autowired
    StudentAttendanceRepository studentAttendanceRepository;
    @Autowired
    public static MarkRepository markRepository;


    @RequestMapping(path = "/students/{id}/marks/{id1}", method = RequestMethod.POST)
    public static  ResponseEntity<?> addMark(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Student student = studentRepository.findOne(id);
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Mark mark = markRepository.findOne(id1);
            if (mark  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                student.getMarks().add(mark);
                studentRepository.save(student);
                mark.setStudent(student);
                markRepository.save(mark);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/students/{id}/studentattendance/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addStudentAttendance(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Student student = studentRepository.findOne(id);
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Attendance studentAttendance = studentAttendanceRepository.findOne(id1);
            if (studentAttendance  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                student.getStudentAttendances().add(studentAttendance);
                studentRepository.save(student);
                studentAttendance.getStudents().add(student);
                studentAttendanceRepository.save(studentAttendance);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
}
