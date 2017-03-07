package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTake {
    private String student = "";
    private String course = "";
    private Double grade = 0.0;

    public void setAll(ResultSet rs) {
        try{
            this.setStudent(rs.getString("student"));
            this.setCourse(rs.getString("course"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStudent() {
        return student;
    }

    public DbTake setStudent(String student) {
        this.student = student;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public DbTake setCourse(String course) {
        this.course = course;
        return this;
    }

    public Double getGrade() {
        return grade;
    }

    public DbTake setGrade(Double grade) {
        this.grade = grade;
        return this;
    }
}
