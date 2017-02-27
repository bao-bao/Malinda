package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTake {
    private Integer studentid = 0;
    private Integer courseid = 0;
    private Double grade = 0.0;

    public void setAll(ResultSet rs) {
        try{
            this.setStudentid(rs.getInt("studentid"));
            this.setStudentid(rs.getInt("courseid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getStudentid() {
        return studentid;
    }

    public DbTake setStudentid(Integer studentid) {
        this.studentid = studentid;
        return this;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public DbTake setCourseid(Integer courseid) {
        this.courseid = courseid;
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
