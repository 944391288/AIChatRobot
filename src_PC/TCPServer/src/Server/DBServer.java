package Server;

import java.sql.*;

public class DBServer {
    private final static String url="jdbc:mysql://localhost:3306/chatrobot";
    private final static String user = "root";
    private final static String psw = "123456";
    private static Connection conn;

    public DBServer(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, psw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql){
        ResultSet rs=null;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    //释放资源 PreparedStatement/ResultSet
    public static void closeResources(PreparedStatement pstmt, ResultSet rs) {
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if(null != pstmt) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}


