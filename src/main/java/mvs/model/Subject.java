package mvs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue
    Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    String mhp;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade=CascadeType.ALL)
    List<Classroom> classrooms;

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMhp() {
        return mhp;
    }

    public void setMhp(String mhp) {
        this.mhp = mhp;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
