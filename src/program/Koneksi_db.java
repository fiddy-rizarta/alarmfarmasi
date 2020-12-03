package program;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Koneksi_db {
    public Statement stat;
    public ResultSet res;
    public Connection con;
    public PreparedStatement pst;
    public int koneksi;
    public Koneksi_db(){
        if (con==null){     
            try {
                String username = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/sik",username,password);
                stat = con.createStatement();                
                koneksi = 1;
            } catch (ClassNotFoundException | SQLException e) {                
                JOptionPane.showMessageDialog(null, "Mohon restart aplikasi alarmnya", "Error Koneksi",JOptionPane.WARNING_MESSAGE);
                koneksi = 0;
                System.out.println("koneksi 0");
            }
        }
    }
    
}
