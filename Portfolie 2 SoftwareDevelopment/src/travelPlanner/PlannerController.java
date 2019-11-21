package travelPlanner;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlannerController implements Initializable {

    DepartureModel departureModel = new DepartureModel();

    @FXML
    private ComboBox<DepartureStations> departureStation;
    @FXML
    public ComboBox arrivalStation;
    @FXML
    private ComboBox departureTime;
    @FXML
    private Button searchJourneyButton;
    @FXML
    public Label yourJourneyLabel;
    @FXML
    public Label depTripLabel;
    @FXML
    public Label depRouteLabel;
    @FXML
    public Label journeyArrival;
    @FXML
    public Label journeyDeparture;


    public void initialize(URL url, ResourceBundle rb) {

        this.departureStation.setItems(FXCollections.observableArrayList(DepartureStations.values()));
        this.arrivalStation.setItems(FXCollections.observableArrayList(ArrivalStations.values()));

    }


    @FXML
    public void depTimeCombo(ActionEvent event) {

        String depStat = departureStation.getValue().toString();
        String depTime = departureTime.getValue().toString();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Here the code should find the correct travel time, from the picked stations.
        String sql = "SELECT " + depStat + ".TripID, " + depStat + ".RouteID " +
                " FROM " + depStat + " " +
                " WHERE Time =" + "'" + depTime + "'";

        System.out.println(sql);

        try
        {

            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/HUMTEK/5. Semester/Software Development/Portfolie 2 SoftwareDevelopment/SDexam.db");
            Statement stmt2 = connection.createStatement();

            resultSet = stmt2.executeQuery(sql);
            while (resultSet.next()) {
                String tidspunkt = resultSet.getString("TripID");
                String route = resultSet.getString("RouteID");

                this.depTripLabel.setText(tidspunkt);
                this.depRouteLabel.setText(route);

            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }


    @FXML
    public void searchJourney(ActionEvent event)
    {
        String arrStat = arrivalStation.getValue().toString();
        String depStat = departureStation.getValue().toString();
        String depTripID = depTripLabel.getText();
        String depRouteID = depRouteLabel.getText();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Here the code should find the correct travel time, from the picked stations.
        String sql1 = "SELECT " + depStat + ".Station, " + depStat + ".Time, " + arrStat + ".Station," + arrStat + ".Time " +
                " FROM " + depStat + " " +
                " INNER JOIN " + arrStat + " " +
                " ON " + depStat + ".RouteID=" + depRouteID + "  AND " + arrStat + ".RouteID=" + depRouteID + " AND " + depStat + ".TripID=" + depTripID + " AND " + arrStat + ".TripID=" + depTripID;
        // This code finds the arrival data.
        String sql2 = "SELECT "+ arrStat + ".Station," + arrStat + ".Time " +
                " FROM " + depStat + " " +
                " INNER JOIN " + arrStat + " " +
                " ON " + depStat + ".RouteID=" + depRouteID + "  AND " + arrStat + ".RouteID=" + depRouteID + " AND " + depStat + ".TripID=" + depTripID + " AND " + arrStat + ".TripID=" + depTripID;

        System.out.println(sql1);
        System.out.println(sql2);


        try
        {
            ArrayList<String> yourJourney = new ArrayList<String>();

            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/HUMTEK/5. Semester/Software Development/Portfolie 2 SoftwareDevelopment/SDexam.db");
            Statement stmt2 = connection.createStatement();

            resultSet = stmt2.executeQuery(sql1);

            while (resultSet.next()) {
                String tidspunkt = resultSet.getString("Time");
                String station = resultSet.getString("Station");

                this.journeyDeparture.setText(station + " " + tidspunkt);



            }

            resultSet = stmt2.executeQuery(sql2);
            while (resultSet.next()) {
                String tidspunkt = resultSet.getString("Time");
                String station = resultSet.getString("Station");

                this.journeyArrival.setText(station + " " + tidspunkt);

            }


        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }



    }

    @FXML
    public void depTimes(ActionEvent event)
    {


        //Here the departure time should update according to the RouteID
        String arrStat = arrivalStation.getValue().toString();
        String depStat = departureStation.getValue().toString();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql = "SELECT " + depStat + ".Time"+
                " FROM " + depStat + " " +
                " INNER JOIN " + arrStat + " " +
                " ON " + depStat + ".RouteID=" + arrStat + ".RouteID AND " + depStat + ".TripID=" + arrStat + ".TripID  AND " + depStat + ".StationID <" + arrStat + ".StationID";


        System.out.println(sql);

       try
        {

            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/HUMTEK/5. Semester/Software Development/Portfolie 2 SoftwareDevelopment/SDexam.db");
            Statement stmt = connection.createStatement();

            resultSet = stmt.executeQuery(sql);
            ArrayList<String> depTime = new ArrayList<String>();
            while (resultSet.next()) {
                String tidspunkt = resultSet.getString("Time");


                //String station = resultSet.getString("Station");

                System.out.println(tidspunkt);
                depTime.add(tidspunkt);

            }
            this.departureTime.setItems(FXCollections.observableArrayList(depTime));

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }



    }



}
