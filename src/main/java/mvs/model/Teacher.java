package mvs.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Teacher {
   @Id
   @GeneratedValue
   Long id;
    String name;
    @Column(unique=true)
    String email;
    String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", cascade=CascadeType.ALL)
    List<Classroom> classrooms;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher() {
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
