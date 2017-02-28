package Model.Dao;

/* Created by AMXPC on 2017/2/27. */

import Model.Dbc.DatabaseConnection;
import Model.Vo.DbCourse;
import Model.Vo.DbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.*;

public class ControlCourseDAO {
    private DatabaseConnection dbconn = null;
    private Connection conn = null;

    public ControlCourseDAO() {
        try {
            dbconn = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = dbconn.getConnection();
    }

    public int assignProfessor(String professor, String course) {
        int message = FAILED;
        String sql = "insert into malinda.teach(professor, course) " +
                "values(?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, professor);
            pstmt.setString(2, course);
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
        return message;
    }

    public int registerCourse(String student, String course) {
        int message = FAILED;
        String sql = "insert into malinda.take(student, course) " +
                "values(?,?)";
        if (!conflict(student, course)) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, student);
                pstmt.setString(2, course);
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

    public boolean conflict(String student, String course) {
        int message = SUCCESS;
        DbCourse dbCourse = new DbCourse();
        String search_sql = "select * from malinda.course where name = ? ";
        String judge_sql = "select * from malinda.take where name = ? ";
        String update_sql = "update malinda.course set number = ? where name = ? ";
        try {
            conn.setAutoCommit(false);
            // do search
            PreparedStatement s_pstmt = conn.prepareStatement(search_sql);
            s_pstmt.setString(1, course);
            ResultSet result = s_pstmt.executeQuery();
            if (result.next()) {
                dbCourse.setAll(result);
                if (dbCourse.getState() == DbCourse.CLOSE || dbCourse.getNumber() == 0) {
                    message = FAILED;
                }
            } else {
                throw new Exception();
            }
            // do judge
            if (message != FAILED) {
                PreparedStatement j_pstmt = conn.prepareStatement(judge_sql);
                j_pstmt.setString(1, student);
                ResultSet rs = j_pstmt.executeQuery();
                if (rs.next()) {
                    while (rs.next()) {
                        if (rs.getInt("year") == dbCourse.getYear() && rs.getString("time") == dbCourse.getTime()) {
                            message = FAILED;
                            break;
                        }
                    }
                }
            }
            // do update
            if(message == SUCCESS) {
                PreparedStatement u_pstmt = conn.prepareStatement(update_sql);
                u_pstmt.setInt(1, dbCourse.getNumber() - 1);
                u_pstmt.setString(2, course);
                int re = u_pstmt.executeUpdate();
                if(re == 0) {
                    message = FAILED;
                }
                else {
                    message = SUCCESS;
                }
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
        return message == FAILED;
    }

    public int changeState(String name, String course, int state) {
        int message = FAILED;
        String sql = "update malinda.course "
                + "set state = ? "
                + "where name = ? ";
        if (validate(name) == SUCCESS) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, state);
                pstmt.setString(2, course);
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

    private int validate(String name) {
        int message = FAILED;
        String sql = "select * from malinda.user where name = ? ";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("level") == DbUser.ADMINISTATOR) {
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

    public int getAllCourse(ArrayList<DbCourse> arrayList) {
        int message = FAILED;
        String sql = "select * from malinda.course ";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                while (rs.next()) {
                    DbCourse dbCourse = new DbCourse();
                    dbCourse.setAll(rs);
                    arrayList.add(dbCourse);
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
