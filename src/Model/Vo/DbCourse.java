package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCourse {
    public static final int OPEN = 1;
    public static final int CLOSE = 2;

    private String name = "";
    private String time = "";
    private Integer year = 0;
    private Integer credit = 0;
    private Integer number = 0;
    private Integer state = 0;

    public void setAll(ResultSet rs) {
        try {
            this.setName(rs.getString("name"));
            this.setTime(rs.getString("time"));
            this.setYear(rs.getInt("year"));
            this.setCredit(rs.getInt("credit"));
            this.setNumber(rs.getInt("number"));
            this.setState(rs.getInt("state"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public Integer getYear() {
        return year;
    }

    public DbCourse setYear(Integer year) {
        this.year = year;
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

    public Integer getState() {
        return state;
    }

    public DbCourse setState(Integer state) {
        this.state = state;
        return this;
    }
}
