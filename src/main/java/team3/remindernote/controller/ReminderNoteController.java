package team3.remindernote.controller;
//
import java.io.IOException;
import team3.remindernote.connect.Connect;
//
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
//
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
//
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ReminderNoteController implements Initializable {
    private Connect connectDatabase;
    private AddRemindController addRemindController;
    // MENU
    @FXML
    private Button ShowAllReminder;
    @FXML
    private Button ShowAllNote;
    // REMIND SET
    @FXML
    private VBox ReminderSet;
    @FXML
private void AddReminder(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/team3/remindernote/AddRemind.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Add Reminder");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @FXML
    private void ExitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setHeaderText("Are you sure you want exit?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Platform.exit();
        } else {
            alert.close();
        }
    }

    @Override
public void initialize(URL location, ResourceBundle resources) {
    connectDatabase = new Connect();
    connectDatabase.getConnect();
    ShowAllRemind();
}
    

    private void ShowAllRemind() {
        try (Statement statement = connectDatabase.getConnect().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM remind")) {

            List<VBox> remindForms = new ArrayList<>();

            VBox reminderSet = new VBox();
            reminderSet.setId("ReminderSet");
            reminderSet.setPrefSize(699, 479);
            reminderSet.setStyle("-fx-stylesheets: @css/TransparentColor.css");

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Timestamp remindTime = resultSet.getTimestamp("remind_time");
                String Id = resultSet.getString("id");

                VBox remindForm = new VBox();
                remindForm.setId("RemindForm");
                remindForm.setPrefSize(699, 113);
                remindForm.setStyle("-fx-border-color: gray; -fx-border-width: 2;");
                remindForm.setPadding(new Insets(0, 0, 0, 10));
                VBox.setMargin(remindForm, new Insets(10, 0, 0, 0));
                HBox hbox1 = new HBox();
                hbox1.setPrefSize(685, 62);

                Label remindTitle = new Label(title);
                remindTitle.setId("RemindTitle");
                remindTitle.setPrefSize(548, 54);
                remindTitle.setFont(new Font(37));

                HBox hbox2 = new HBox();
                hbox2.setPrefSize(141, 54);

                Button deleteButton = new Button("DELETE");
                deleteButton.setId("DeleteButton");
                deleteButton.setPrefSize(74, 33);
                deleteButton.setStyle("-fx-background-color: white;");
                deleteButton.setTextFill(Color.RED);
                deleteButton.setFont(new Font(15));


               Label slashLabel = new Label("/");
                slashLabel.setPrefSize(3, 30);
                slashLabel.setFont(new Font(16));

                Button editButton = new Button("EDIT");
                editButton.setId("EditButton");
                editButton.setPrefSize(58, 33);
                editButton.setStyle("-fx-background-color: white;");
                editButton.setFont(new Font(15));

                hbox2.getChildren().addAll(deleteButton, slashLabel, editButton);
                hbox1.getChildren().addAll(remindTitle, hbox2);
                remindForm.getChildren().add(hbox1);

                HBox hbox3 = new HBox();
                hbox3.setPrefSize(200, 100);

                Label startAtLabel = new Label("Start at:");
                startAtLabel.setPrefSize(92, 52);
                startAtLabel.setTextFill(Color.web("#7c7c7c"));
                startAtLabel.setFont(new Font(24));

                Label timeRemind = new Label(remindTime.toString());
                timeRemind.setId("TimeRemind");
                timeRemind.setPrefSize(340, 53);
                timeRemind.setFont(new Font(24));

                hbox3.getChildren().addAll(startAtLabel, timeRemind);
                remindForm.getChildren().add(hbox3);

                remindForm.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));

                remindForms.add(remindForm); 
            }

            reminderSet.getChildren().addAll(remindForms); 
ReminderSet.getChildren().clear();
ReminderSet.getChildren().add(reminderSet); 
        } catch (SQLException e) {
            e.printStackTrace();
        
    }
    }
    public void UpdateListRemind(){
    ShowAllRemind();
    }
}