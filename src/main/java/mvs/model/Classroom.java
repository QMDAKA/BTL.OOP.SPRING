package mvs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Classroom {
    @Id
    @GeneratedValue
    Long id;
    String mslh;
    String timeBegin;
    String timeEnd;
    String address;
    String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Attendance> getStudentAttendances() {
        return attendances;
    }

    public void setStudentAttendances(List<Attendance> studentAttendances) {
        this.attendances = studentAttendances;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Teacher.class)
    Teacher teacher;


    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Subject.class)
    @JsonIgnore
    Subject subject;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom", cascade=CascadeType.ALL)
    List<Attendance> attendances;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade=CascadeType.ALL)
    List<Mark> marks;

    @ManyToMany(mappedBy = "classrooms")
    List<Student> students;

    public Classroom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMslh() {
        return mslh;
    }

    public void setMslh(String mslh) {
        this.mslh = mslh;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


   public void removeMarkNotInClass(){
        for(Student s:students){
            for(int i=0;i<s.getMarks().size();i++){
                if(s.getMarks().get(i).getClassroom().getId()!=this.id){
                    Mark a=s.getMarks().get(i);
                    s.getMarks().remove(a);
                }
            }
        }
    }
}
