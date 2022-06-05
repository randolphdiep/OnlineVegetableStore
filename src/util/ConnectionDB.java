package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConnectionDB {

    static int countConection = 0;
    static int countQuery = 0;
    static int countUpdate = 0;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    String DB_Name = null;
    String user_Name = null;
    String pass = null;

    String ipAddress = "localhost";

    public ConnectionDB() {
        checkDriver();
        DB_Name = "quanlycuahangdienthoai";
        user_Name = "root";
        pass = "";
        setupConnect();
    }

    public ConnectionDB(String DB_Name) {
        checkDriver();
        this.DB_Name = DB_Name;
    }

    public ConnectionDB(String DB_Name, String user_Name, String pass) {
        checkDriver();
        this.DB_Name = DB_Name;
        this.user_Name = user_Name;
        this.pass = pass;
        setupConnect();
    }

    // Káº¿t ná»‘i tá»›i DB
    private void setupConnect() {
        try {
            String url = "jdbc:mysql://" + ipAddress + "/" + DB_Name + "?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, user_Name, pass);
            stmt = conn.createStatement();
            countConection++;
            System.out.println("**\n" + countConection + ": Success! Ä�Ã£ káº¿t ná»‘i tá»›i '" + DB_Name + "'");

        } catch (SQLException e) {
            System.err.println("-- ERROR! KhÃ´ng thá»ƒ káº¿t ná»‘i tá»›i '" + DB_Name + "'");
            JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng thá»ƒ káº¿t ná»‘i tá»›i '" + DB_Name + "'");
        }
    }

    // Ä‘Äƒng nháº­p
    public void logIn(String user_Name, String pass) {
        this.user_Name = user_Name;
        this.pass = pass;
        setupConnect();
    }

    // láº¥y data theo cÃ¢u query
    public ResultSet sqlQuery(String qry) {
        if (checkConnect()) {
            try {
                rset = stmt.executeQuery(qry);
                countQuery++;
                System.out.println(countQuery + ": Success Query! " + qry);
                return rset;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng thá»ƒ láº¥y dá»¯ liá»‡u tá»« " + DB_Name + "\n" + ex.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }

    // ghi data theo cÃ¢u update
    public Boolean sqlUpdate(String qry) {
        if (checkConnect()) {
            try {
                stmt.executeUpdate(qry);
                countUpdate++;
                System.out.println(countUpdate + ": Success Update! " + qry);
                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng thá»ƒ ghi dá»¯ liá»‡u xuá»‘ng " + DB_Name + "\n" + ex.getLocalizedMessage());
                return false;
            }
        }
        return false;
    }

    // Ä‘Ã³ng connection
    public void closeConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            System.out.println("Success! Ä�Ã³ng káº¿t ná»‘i tá»›i '" + DB_Name + "' thÃ nh cÃ´ng.\n**");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng thá»ƒ Ä‘Ã³ng káº¿t ná»‘i tá»›i " + DB_Name + "\n" + ex.getLocalizedMessage());
        }
    }

    // check logIn, connect
    public Boolean checkConnect() {
        if (conn == null || stmt == null) {
            JOptionPane.showMessageDialog(null, "-- ERROR! ChÆ°a thiáº¿t láº­p káº¿t ná»‘i tá»›i '" + DB_Name + "'. Vui lÃ²ng Ä‘Äƒng nháº­p Ä‘á»ƒ thiáº¿t láº­p káº¿t ná»‘i!");
            return false;
        }
        return true;
    }

    // check driver
    private void checkDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng tÃ¬m tháº¥y Driver mySql");
        }
    }

    // láº¥y headers cá»§a table tÃªn tableName trong db nÃ y - Ä‘Ã£ cÃ³ cÃ¡ch khÃ¡c, khÃ´ng dÃ¹ng hÃ m nÃ y ná»¯a
    public ArrayList<String> getHeaders(String tableName) {
        ArrayList<String> headers = new ArrayList<>();
        if (checkConnect()) {
            try {
                ResultSetMetaData rsMetaData = sqlQuery("SELECT * FROM " + tableName).getMetaData();
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    headers.add(rsMetaData.getColumnName(i));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "-- ERROR! KhÃ´ng thá»ƒ láº¥y headers cá»§a " + tableName + " trong " + DB_Name);
            }
        }
        return headers;
    }
}
