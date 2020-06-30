package com.wrpxcx.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QQMain {

    private static Stage stage;

    public void init() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("qq.fxml"));
        Stage qqPanel = new Stage();
        qqPanel.setTitle("QQ");
        qqPanel.setScene(new Scene(root));
        stage = qqPanel;
        qqPanel.show();
    }

    public void close(){

        stage.close();
    }

    public static void updateList(String data) {

        stage.setTitle("ha, wo gai ming le");
    }

}
