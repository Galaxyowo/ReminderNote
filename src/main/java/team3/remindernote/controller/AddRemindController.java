package team3.remindernote.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import team3.remindernote.dao.RemindDao;
import team3.remindernote.model.Remind;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRemindController {

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_time;

    @FXML
    private DatePicker txt_date;

    private RemindDao remindDao = new RemindDao();

    @FXML
    private void createRemind(ActionEvent event) throws ParseException {
        Remind remind = new Remind();
        remind.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        remind.setTitle(txt_title.getText());
        String datestr = txt_date.getValue().toString() + " " + txt_time.getText();
        System.out.println(datestr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date parsedDate = dateFormat.parse(datestr);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        remind.setRemindTime(timestamp);
        remindDao.insertRemind(remind);
        Message.getMess("Add remind successful !!!");

        // Đóng cửa sổ hiện tại sau khi thêm ghi nhớ thành công
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Cập nhật giao diện danh sách ghi nhớ
        updateReminderNoteController();
    }

    // Phương thức để cập nhật giao diện hiển thị danh sách ghi nhớ
    private void updateReminderNoteController() {
        ReminderNoteController reminderNoteController = new ReminderNoteController();
        reminderNoteController.UpdateListRemind();
    }
}