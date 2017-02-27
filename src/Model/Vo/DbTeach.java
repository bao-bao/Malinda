package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTeach {
    private Integer professorid = 0;
    private Integer courseid = 0;

    public void setAll(ResultSet rs) {
        try{
            this.setProfessorid(rs.getInt("professorid"));
            this.setCourseid(rs.getInt("courseid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getProfessorid() {
        return professorid;
    }

    public DbTeach setProfessorid(Integer professorid) {
        this.professorid = professorid;
        return this;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public DbTeach setCourseid(Integer courseid) {
        this.courseid = courseid;
        return this;
    }
}
