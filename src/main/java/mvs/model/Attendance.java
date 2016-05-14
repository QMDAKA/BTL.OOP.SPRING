package mvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Attendance {
    @Id
    @GeneratedValue
    Long id;

    Date date;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Classroom.class)
    Classroom classroom;
    @JsonIgnore
    @ManyToMany(mappedBy = "attendances")
    List<Student> students;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


}
