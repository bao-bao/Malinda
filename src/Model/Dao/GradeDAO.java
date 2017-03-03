package Model.Dao;

/* Created by AMXPC on 2017/2/27. */

import Model.Dbc.DatabaseConnection;
import Model.Vo.DbCourse;
import Model.Vo.DbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.*;

public class GradeDAO {
    private DatabaseConnection dbconn = null;
    private Connection conn = null;

    public GradeDAO() {
        try {
            dbconn = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = dbconn.getConnection();
    }

    public int submitGrade(String professor, String student, String course, double grade) {
        int message = FAILED;
        String sql = "update malinda.take "
                + "set grade = ? "
                + "where name = ? and course = ? ";
        if(validate(professor) == SUCCESS) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, grade);
                pstmt.setString(2, student);
                pstmt.setString(3, course);
                int result = pstmt.executeUpdate();
                if (result == 0) {
                    message = FAILED;
                } else {
                    message = SUCCESS;
                }
            } catch (Exception e) {
                message = EXCEPTION;
                e.printStackTrace();
            } finally {
                try {
                    if (message == SUCCESS) {
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                    dbconn.close();
                } catch (Exception e) {
                    message = EXCEPTION;
                    e.printStackTrace();
                }
            }
        }
        return message;
    }

    public int validate(String name) {
        int message = FAILED;
        String sql = "select * from malinda.user where name = ? ";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                if(rs.getInt("level") == DbUser.PROFESSOR) {
                    message = SUCCESS;
                }
            }
        } catch (Exception e) {
            message = EXCEPTION;
            e.printStackTrace();
        } finally {
            try {
                dbconn.close();
            } catch (Exception e) {
                message = EXCEPTION;
                e.printStackTrace();
            }
        }
        return message;
    }

    public int getTookStudent(String course, ArrayList<DbUser> arrayList) {
        int message = FAILED;
        String sql = "select * from malinda.user where name in (select student from malinda.take where course = ?) ";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                while (rs.next()) {
                    DbUser dbUser = new DbUser();
                    dbUser.setAll(rs);
                    arrayList.add(dbUser);
                }
                message = SUCCESS;
            }
        } catch (Exception e) {
            message = EXCEPTION;
            e.printStackTrace();
        } finally {
            try {
                dbconn.close();
            } catch (Exception e) {
                message = EXCEPTION;
                e.printStackTrace();
            }
        }
        return message;
    }
}
