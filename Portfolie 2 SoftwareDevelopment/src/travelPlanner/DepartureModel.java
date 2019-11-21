package travelPlanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class DepartureModel {
    Connection connection;

    public DepartureModel()
    {
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        if(this.connection == null)
        {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected()
    {
        return this.connection !=null;
    }


    public boolean DepartureChoice(String depStat, String arrStat) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT ?" +
                     "FROM ? " +
                     "INNER JOIN ?" +
                     "ON ?.RouteID = ?.RouteID";

        try {

            resultSet = preparedStatement.executeQuery();

            boolean bool1;

            if(resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException ex){
            return false;
        }

        // Close a connection

        finally{

            {
                preparedStatement.close();
                resultSet.close();
            }
        }

    }

}
