package mvs.controller;

import mvs.model.*;
import mvs.repository.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Quang Minh on 4/17/2016.
 */
@RestController
public class ClassroomController {
    @Autowired
    ClassroomRepository classroomRepository;
    @Autowired
    MarkRepository markRepository;

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentAttendanceRepository studentAttendanceRepository;
    @RequestMapping(path = "/classrooms/{id}/teachers/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addTeacher(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Teacher teacher = teacherRepository.findOne(id1);
            if (teacher  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                classroom.setTeacher(teacher);
                classroomRepository.save(classroom);
                teacher.getClassrooms().add(classroom);
                teacherRepository.save(teacher);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/classrooms/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getClassroom(
            @PathVariable(value = "id") long id
    ) {
        Response response = new Response();
        Classroom classroom = classroomRepository.findOne(id);
        if (classroom == null) {
            response.setSuccess(0);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            response.setSuccess(1);
            classroom.removeMarkNotInClass();
            response.setResponse(classroom);

            return ResponseEntity.ok(response);

        }
    }



    @RequestMapping(path = "/classrooms/{id}/teachers/{id1}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTeacher(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){
        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Teacher teacher =teacherRepository.findOne(id1);
            if (teacher == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else{

                teacher.getClassrooms().remove(classroom);
                teacherRepository.save(teacher);
                classroom.setTeacher(new Teacher());
                classroomRepository.save(classroom);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }
    }
    @RequestMapping(path = "/classrooms/{id}/students/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addStudents(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Student student = studentRepository.findOne(id1);
            if (student  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {

                classroom.getStudents().add(student);
                Mark mark=new Mark();
                mark.setStudent(student);
                mark.setDiemCuoiKy(new Float(0));
                mark.setDiemGiuaKy(new Float(0));
                mark.setDiemTongKet(new Float(0));
                mark.setClassroom(classroom);
                markRepository.save(mark);
                classroom.getMarks().add(mark);

                classroomRepository.save(classroom);
                student.getClassrooms().add(classroom);
                studentRepository.save(student);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/classrooms/{id}/students/{id1}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGroups(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){
        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Student student =studentRepository.findOne(id1);
            if (student == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else{
                Classroom a=classroom;
                classroom.getStudents().remove(student);
                classroomRepository.save(classroom);
                student.getClassrooms().remove(a);
                studentRepository.save(student);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }
    }

    @RequestMapping(path = "/classrooms/{id}/subjects/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addSubject(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Subject subject = subjectRepository.findOne(id1);
            if (subject  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                classroom.setSubject(subject);
                classroomRepository.save(classroom);
                subject.getClassrooms().add(classroom);
                subjectRepository.save(subject);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/classrooms/{id}/subjects/{id1}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSubject(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){
        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Subject subject =subjectRepository.findOne(id1);
            if (subject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else{

                subject.getClassrooms().remove(classroom);
                subjectRepository.save(subject);
                classroom.setSubject(new Subject());
                classroomRepository.save(classroom);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }
    }

    @RequestMapping(path = "/classrooms/{id}/marks/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addMark(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Mark mark = markRepository.findOne(id1);
            if (mark  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                classroom.getMarks().add(mark);
                classroomRepository.save(classroom);
                mark.setClassroom(classroom);
                markRepository.save(mark);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/classrooms/{id}/studentattendance/{id1}", method = RequestMethod.POST)
    ResponseEntity<?> addStudentAttendance(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "id1") long id1
    ){

        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Attendance studentAttendance = studentAttendanceRepository.findOne(id1);
            if (studentAttendance  == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else {
                classroom.getStudentAttendances().add(studentAttendance);
                classroomRepository.save(classroom);
                studentAttendance.setClassroom(classroom);
                studentAttendanceRepository.save(studentAttendance);
                return ResponseEntity.status(HttpStatus.OK).body(null);

            }
        }

    }
    @RequestMapping(path = "/classrooms/{id}/subject", method = RequestMethod.GET)
    ResponseEntity<?> getSubject(
            @PathVariable(value = "id") long id
    ){
        Response response=new Response();
        Classroom classroom = classroomRepository.findOne(id);
        if(classroom==null){
            response.setSuccess(0);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            response.setSuccess(1);
            response.setResponse(classroom.getSubject());
            return ResponseEntity.ok(response);
        }

    }
   @RequestMapping(path = "/classrooms", method = RequestMethod.POST)
    ResponseEntity<?> addClassroom(
            @RequestBody Classroom a
    ){
        if(a==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
        else {
            Date date=new Date(26897898);
            ArrayList<Attendance> arrayList=new ArrayList<Attendance>();
            for(int i=0;i<7;i++){
                Attendance attendance=new Attendance();
                Date d=new Date(date.getTime()+i*7*86400*1000);
                attendance.setDate(d);
                attendance.setClassroom(a);
                arrayList.add(attendance);
                studentAttendanceRepository.save(attendance);
            }
            a.setStudentAttendances(arrayList);
            classroomRepository.save(a);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

    }
    @RequestMapping(path = "/classrooms", method = RequestMethod.GET)
    ResponseEntity<?> getClassrooms(
    ){
       List<Classroom> arrayList=classroomRepository.findAll();
        return ResponseEntity.ok(arrayList);

    }
    @RequestMapping(path = "/classrooms/registerClassroom/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getRClassrooms(
            @PathVariable(value = "id") long id
    ){
        Teacher teacher=teacherRepository.findOne(id);
        Response response=new Response();
        if(teacher==null){
            response.setSuccess(0);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else{
            List<Long> listId=new ArrayList<Long>();
            for(Classroom classroom:teacher.getClassrooms()){
                listId.add(classroom.getId());
            }
            List<Classroom> classrooms=classroomRepository.findByIdNotIn(listId);
            for(Classroom classroom:classrooms){
                if(classroom.getTeacher()!=null){
                    classrooms.remove(classroom);
                }
            }
            response.setSuccess(1);
            response.setResponse(classrooms);
            return ResponseEntity.ok(response);
        }


    }
}
