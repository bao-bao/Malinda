package Model.Vo;

/* Created by AMXPC on 2017/2/27. */

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbUser {
    public static final int ADMINISTATOR = 0;
    public static final int STUDENT = 1;
    public static final int PROFESSOR = 2;

    private String name = "";
    private String password = "";
    private String major = "";
    private Integer age = 0;
    private String education = "";
    private Integer level = -1;

    public void setAll(ResultSet rs) {
        try {
            this.setName(rs.getString("name"));
            this.setPassword(rs.getString("password"));
            this.setMajor(rs.getString("major"));
            this.setAge(rs.getInt("age"));
            this.setEducation(rs.getString("education"));
            this.setLevel(rs.getInt("level"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public DbUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DbUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public DbUser setMajor(String major) {
        this.major = major;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DbUser setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public DbUser setEducation(String education) {
        this.education = education;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public DbUser setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
