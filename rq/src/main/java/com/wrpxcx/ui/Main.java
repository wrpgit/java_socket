package com.wrpxcx.ui;

import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.ui.testThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Client client = ClientSingleton.getClient();
        client.init();

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("QQ");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        stage = primaryStage;
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void closeLoginPage() {

        System.out.println("stage" + stage);
        new testThread(stage, 1).start();
    }
}
