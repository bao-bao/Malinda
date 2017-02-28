package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTeach {
    private String professor = "";
    private String course = "";

    public void setAll(ResultSet rs) {
        try{
            this.setProfessor(rs.getString("professor"));
            this.setCourse(rs.getString("course"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getProfessor() {
        return professor;
    }

    public DbTeach setProfessor(String professor) {
        this.professor = professor;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public DbTeach setCourse(String course) {
        this.course = course;
        return this;
    }
}
