package team3.remindernote;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    private static final String FIRST_TIME_FXML = "FirstTime";
    private static final String ALL_REMINDER_FXML = "AllReminder";
   private static final String FILE_PATH = "C:/Chest/ReminderNote/ReminderNote/src/main/java/team3/remindernote/User.txt";

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        if (checkFileExists()) {
            loadAllReminderFXML(stage);
        } else {
            loadFirstTimeFXML(stage);
        }
    }

    private boolean checkFileExists() {
        File file = new File("C:/Chest/ReminderNote/ReminderNote/src/main/java/team3/remindernote/User.txt");
        return file.exists();
    }

    private void loadFirstTimeFXML(Stage stage) throws IOException {
        scene = new Scene(loadFXML("FirstTime"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void loadAllReminderFXML(Stage stage) throws IOException {
        scene = new Scene(loadFXML("AllReminder"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}