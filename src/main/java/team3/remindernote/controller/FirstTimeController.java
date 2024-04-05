package team3.remindernote.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FirstTimeController {
    private static final String FILE_PATH = "C:/Chest/ReminderNote/ReminderNote/src/main/java/team3/remindernote/User.txt";

    @FXML
    private TextField nameField;

    @FXML
    private void initialize() {

    }

    @FXML
    private void saveName(ActionEvent event) {
        String name = nameField.getText();
        saveNameToFile(name);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save name");
        alert.setHeaderText(null);
        alert.setContentText("Save name complete!");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/team3/remindernote/AllReminder.fxml"));
        Parent allReminderRoot;
        try {
            allReminderRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene allReminderScene = new Scene(allReminderRoot);
        Stage currentStage = (Stage) nameField.getScene().getWindow();
        currentStage.setScene(allReminderScene);
        currentStage.setMaximized(true);

        alert.showAndWait();
    }

    private void saveNameToFile(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}