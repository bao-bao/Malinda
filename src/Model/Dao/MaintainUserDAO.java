package Model.Dao;

/* Created by AMXPC on 2017/2/27. */

import Model.Dbc.DatabaseConnection;
import Model.Vo.DbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Model.Dao.DAOFactory.*;

public class MaintainUserDAO {
    private DatabaseConnection dbconn = null;
    private Connection conn = null;

    public MaintainUserDAO() {
        try {
            dbconn = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = dbconn.getConnection();
    }

    public int maintainUser(String name, DbUser dbuser, int level) {
        int message = FAILED;
        String sql = "insert into malinda.user(name, password, major, age, education, level) " +
                "values(?,?,?,?,?,?)";
        if(validate(name) == SUCCESS) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dbuser.getName());
                pstmt.setString(2, dbuser.getName());
                pstmt.setString(3, dbuser.getMajor());
                pstmt.setInt(4, dbuser.getAge());
                pstmt.setString(5, dbuser.getEducation());
                pstmt.setInt(6, level);
                int result = pstmt.executeUpdate();
                if(result == 0) {
                    message = FAILED;
                }
                else {
                    message = SUCCESS;
                }
            } catch (Exception e) {
                message = EXCEPTION;
                e.printStackTrace();
            } finally {
                try {
                    if (message == FAILED) {
                        conn.rollback();
                    } else {
                        conn.commit();
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
                if(rs.getInt("level") == DbUser.ADMINISTRATOR) {
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

    public int changePassword(String user, String old_password, String new_password) {
        int message = FAILED;
        String sql = "update malinda.user "
                + "set password = ? "
                + "where name = ? and password = ? ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, new_password);
            pstmt.setString(2, user);
            pstmt.setString(3, old_password);
            int result = pstmt.executeUpdate();
            if(result == 0) {
                message = FAILED;
            }
            else {
                message = SUCCESS;
            }
        } catch (Exception e) {
            message = EXCEPTION;
            e.printStackTrace();
        } finally {
            try {
                if(message == SUCCESS) {
                    conn.commit();
                }
                else {
                    conn.rollback();
                }
                dbconn.close();
            } catch (Exception e) {
                message = EXCEPTION;
                e.printStackTrace();
            }
        }
        return message;
    }
}
