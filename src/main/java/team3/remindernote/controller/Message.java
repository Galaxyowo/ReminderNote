package team3.remindernote.controller;

import javafx.scene.control.Alert;

public class Message {

    public static void getMess(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(str);
        alert.show();
    }
}

