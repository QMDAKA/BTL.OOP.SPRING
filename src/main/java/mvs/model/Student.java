package mvs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Student {
    @Id
    @GeneratedValue
    Long id;
    @Column(unique=true)
    String Name;
    String Grade;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_classrooms", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "classroom_id", referencedColumnName = "id"))
    List<Classroom> classrooms;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_studentattendaces", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "attendance_id", referencedColumnName = "id"))
    List<Attendance> attendances;

    public List<Attendance> getStudentAttendances() {
        return attendances;
    }

    public void setStudentAttendances(List<Attendance> studentAttendances) {
        this.attendances = studentAttendances;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade=CascadeType.ALL)
    List<Mark> marks;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }



    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
