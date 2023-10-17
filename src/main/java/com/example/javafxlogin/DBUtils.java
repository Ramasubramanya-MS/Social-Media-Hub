package com.example.javafxlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DBUtils {

    public static void changeScene (ActionEvent event, String fxmlFile, String title, String username, String VIPuser){

        Parent root = null;
        if(username != null && VIPuser != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, VIPuser);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

            else {
                try{
                    root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));

            } catch (IOException e){
                    e.printStackTrace();
                }
        }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
    }

    public static void signUpUser (ActionEvent event, String username, String password, String favChannel) throws SQLException {
//        Connection
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video","root", "Msr123");
            psCheckUserExists = connection.prepareStatement("SELECT * from users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already Exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }
            else{
                System.out.println(username+" "+ password+" "+ favChannel);
                psInsert = connection.prepareStatement("INSERT INTO users (username, password, favChannel) Values (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, favChannel);
                psInsert.executeUpdate();

                DBUtils.changeScene(event, "logged-in.fxml","Welcome!", username, favChannel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(psInsert !=  null){
                try{
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection !=  null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video","root", "Msr123");
            preparedStatement = connection.prepareStatement("SELECT password, favChannel FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }else{
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrivedChannel = resultSet.getString("favChannel");
                    if(retrievedPassword.equals(password)){
                        DBUtils.changeScene(event, "logged-in.fxml", "Welcome! ", username, retrivedChannel);
                    }
                    else{
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Provided credentials are incorrect!");
                        alert.show();
                    }
                }

            }

        }catch(SQLException e){
//            System.out
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(preparedStatement !=  null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection !=  null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
