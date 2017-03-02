package Model.Dbc;

/* Created by AMXPC on 2017/2/27. */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://100.64.11.43:3306";
    private static final String DBuser = "malinda";
    private static final String DBPASSWORD = "123";
    private Connection conn = null;

    public DatabaseConnection() throws Exception {
        try {
            Class.forName(DBDRIVER);// 加载驱动程序
            conn = DriverManager.getConnection(DBURL, DBuser, DBPASSWORD);// 连接数据库
            System.out.println(conn.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc Driver cannot found.");
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            System.out.println("DB connection failed.");
            e.printStackTrace();
            throw e;
        }
    }

    public Connection getConnection() { // 取得数据库连接
        return this.conn;
    }

    public void close() throws Exception {  // 数据库关闭操作
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
