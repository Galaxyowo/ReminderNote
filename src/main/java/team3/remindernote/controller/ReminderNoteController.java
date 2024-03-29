package team3.remindernote.controller;

import javafx.fxml.FXML;
import team3.remindernote.ConnectDatabase;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
    private ConnectDatabase connectDatabase;

    // MENU
    @FXML
    private Button ShowAllReminder;
    @FXML
    private Button ShowAllNote;
    // REMIND SET
    @FXML
    private Button AddReminder;
    @FXML
    private VBox ReminderSet;

    @FXML
    private void DeleteRemind(ActionEvent event) {
        Button deleteButton = (Button) event.getSource();
        VBox reminderBox = getReminderBoxFromButton(deleteButton);

        if (showConfirmationDialog("Xóa Nhắc nhở", "Bạn có chắc chắn muốn xóa nhắc nhở này?")) {
            removeReminderBox(reminderBox);
            String remindId = getReminderIdFromBox(reminderBox);
            deleteReminderFromDatabase(remindId);
        }
    }

    @FXML
    private void ExitAction(ActionEvent event) {
        if (showConfirmationDialog("Thoát?", "Bạn có chắc chắn muốn thoáng chương trình?")) {
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDatabase = new ConnectDatabase();
        connectDatabase.connect();
        showAllReminders();
    }

    private void showAllReminders() {
        List<remind> reminders = retrieveRemindersFromDatabase();

        for (Reminder reminder : reminders) {
            VBox reminderBox = createReminderBox(reminder);
            ReminderSet.getChildren().add(reminderBox);
        }
    }

    private List<Reminder> retrieveRemindersFromDatabase() {
        List<Reminder> reminders = new ArrayList<>();

        try {
            Statement stmt = connectDatabase.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reminder");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp timestamp = rs.getTimestamp("timestamp");

                Reminder reminder = new Reminder(id, title, description, timestamp);
                reminders.add(reminder);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            handleDatabaseException(e);
        }

        return reminders;
    }

    private VBox createReminderBox(Reminder reminder) {
        VBox reminderBox = new VBox();
        reminderBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        reminderBox.setPadding(new Insets(10));
        reminderBox.setSpacing(10);

        HBox titleBox = new HBox();
        titleBox.setSpacing(10);

        Button deleteButton = new Button("Xóa");
        deleteButton.setOnAction(this::DeleteRemind);

        titleBox.getChildren().addAll(new Button(reminder.getTitle()), deleteButton);
        reminderBox.getChildren().addAll(titleBox, new HBox(new Button(reminder.getDescription())));

        return reminderBox;
    }

    private VBox getReminderBoxFromButton(Button button) {
        Node parent = button.getParent();
        while (!(parent instanceof VBox)) {
            parent = parent.getParent();
        }
        return (VBox) parent;
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE;
    }

    private String getReminderIdFromBox(VBox reminderBox) {
        // Implement this method based on how you store the reminder ID in the reminderBox
        // For example, you can set an ID as a property of the reminderBox and retrieve it here
        return null;
    }

    private void removeReminderBox(VBox reminderBox) {
        ReminderSet.getChildren().remove(reminderBox);
    }

    private void deleteReminderFromDatabase(String reminderId) {
        try {
            String sql = "DELETE FROM Reminder WHERE id = ?";
            PreparedStatement pstmt = connectDatabase.getConnection().prepareStatement(sql);
            pstmt.setString(1, reminderId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            handleDatabaseException(e);
        }
    }

    private void handleDatabaseException(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Lỗi cơ sở dữ liệu");
        alert.setContentText("Đã xảy ra lỗi khi truy cập cơ sở dữ liệu.");
        alert.showAndWait();
        e.printStackTrace();
    }
}