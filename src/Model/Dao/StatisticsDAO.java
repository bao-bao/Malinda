package Model.Dao;

/* Created by AMXPC on 2017/3/2. */

import Model.Dbc.DatabaseConnection;
import Model.Vo.DbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static Model.Dao.DAOFactory.*;
import static Model.Vo.DbUser.*;

public class StatisticsDAO {
    private DatabaseConnection dbconn = null;
    private Connection conn = null;

    public StatisticsDAO() {
        try {
            dbconn = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = dbconn.getConnection();
    }

    public int getNumber(int kind, ArrayList<Integer> arrayList) {
        int message = FAILED;
        String sql_1 = "select count(*) from malinda.user where level = ? ";
        String sql_2 = "select count(*) from malinda.course ";
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt;
            if(kind == COURSE) {
                pstmt = conn.prepareStatement(sql_2);
            }
            else {
                pstmt = conn.prepareStatement(sql_1);
                pstmt.setInt(1, kind);
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                arrayList.add(rs.getInt(1));
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
