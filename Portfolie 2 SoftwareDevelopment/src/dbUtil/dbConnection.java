package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

     public static final String SQCONN = "jdbc:sqlite:D:/HUMTEK/5. Semester/Software Development/Portfolie 2 SoftwareDevelopment/SDexam.db";


    public static java.sql.Connection getConnection() throws SQLException
    {
          try {
              Class.forName("org.sqlite.JDBC");
              return DriverManager.getConnection(SQCONN);
          } catch (ClassNotFoundException ex) {
              ex.printStackTrace();
          }
          return null;

    }

}
