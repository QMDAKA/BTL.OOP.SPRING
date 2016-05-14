package mvs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@Entity
public class Mark {
    @Id
    @GeneratedValue
    Long id;

    Float diemGiuaKy;
    Float diemCuoiKy;
    Float diemTongKet;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Classroom.class)
    Classroom classroom;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Student.class)
    Student student;

    public Mark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(Float diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public Float getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(Float diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    public Float getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(Float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
