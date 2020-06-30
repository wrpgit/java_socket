package com.wrpxcx.ui.loginPage;

import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.ui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class LoginController {


    @FXML
    private Button mButton;

    @FXML
    private Label mLabel;


    @FXML
    public void onButtonClick(ActionEvent event) {
        Client client = ClientSingleton.getClient();


        client.sendMessage("{id: 123, password: 345}");
        Main.closeLoginPage();

    }

}
