package com.example.javafxlogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML private Button button_signup;

    @FXML private Button button_log_in;

    @FXML private RadioButton rb_wittcode;

    @FXML private RadioButton rb_someone_else;


    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_wittcode.setToggleGroup(toggleGroup);
        rb_someone_else.setToggleGroup(toggleGroup);


        rb_wittcode.setSelected(true);


        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
//                System.out.println(toggleName);
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    try {
                        DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText(), toggleName);
//                        System.out.println(tf_username.getText()+" "+ tf_password.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    System.out.println("Please fill in all the ");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log In!", null, null);
//                DBUtils.changeScene(event, "com/example/javafxlogin/sample.fxml", "Log In!");
            }
        });
    }
}
