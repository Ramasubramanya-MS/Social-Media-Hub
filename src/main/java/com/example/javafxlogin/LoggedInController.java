package com.example.javafxlogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    Label label_welcome;

    @FXML
    Label label_vip_user;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log In!", null, null);
            }
        });
    }

    public void setUserInformation(String username, String vipUser){
//        System.out.println(username+ "" + vipUser);
        label_welcome.setText(("Welcome" + username +  "!!!"));
        label_vip_user.setText(("You are a  " + vipUser + "!"));

    }


}
