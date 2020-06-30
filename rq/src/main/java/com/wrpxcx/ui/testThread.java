package com.wrpxcx.ui;

import javafx.application.Platform;
import javafx.stage.Stage;

public class testThread extends Thread{

    private Stage stage;
    private int cop;

    public testThread(Stage stage, int cop){
        this.stage = stage;
        this.cop = cop;
    }

    @Override
    public void run() {
        super.run();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处

                if(cop == 1) {
                    stage.close();
                }
            }
        });
    }
}
