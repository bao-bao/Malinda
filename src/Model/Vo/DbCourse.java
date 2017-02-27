package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCourse {
    private Integer courseid = 0;
    private String name = "";
    private String time = "";
    private Integer credit = 0;
    private Integer number = 0;

    public void setAll(ResultSet rs) {
        try {
            this.setName(rs.getString("name"));
            this.setTime(rs.getString("time"));
            this.setCredit(rs.getInt("credit"));
            this.setNumber(rs.getInt("number"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public DbCourse setName(String name) {
        this.name = name;
        return this;
    }

    public String getTime() {
        return time;
    }

    public DbCourse setTime(String time) {
        this.time = time;
        return this;
    }

    public Integer getCredit() {
        return credit;
    }

    public DbCourse setCredit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public DbCourse setNumber(Integer number) {
        this.number = number;
        return this;
    }
}
