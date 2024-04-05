package team3.remindernote.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import team3.remindernote.dao.RemindDao;
import team3.remindernote.model.Remind;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class EditRemindController {

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_time;

    @FXML
    private DatePicker txt_date;

    @FXML
    private TextArea txt_content;

    @FXML
    private TextField txt_id;


    private String linkAnh = null;
    private String typeFile = null;

    private RemindDao remindDao = new RemindDao();

    @FXML
    void chooseFile(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            String s = f.getName();
            System.out.println(s);
            Path urlFile = f.toPath();
            linkAnh = urlFile.toString();
            System.out.println(linkAnh);
            typeFile = Optional.ofNullable(s)
                    .filter(fl -> fl.contains("."))
                    .map(fl -> fl.substring(s.lastIndexOf(".") + 1)).get();
            System.out.println(typeFile);
        }
    }

    @FXML
    void createRemind(ActionEvent event) throws ParseException {
        if(txt_id.getText().equals("")){
            Message.getMess("Id do not null");
            return;
        }
        try {
            Integer.valueOf(txt_id.getText());
        }catch (Exception e){
            Message.getMess("Id must is number");
            return;
        }
        if(remindDao.checkById(Integer.valueOf(txt_id.getText())) == false){
            Message.getMess("not found remind");
            return;
        }
        Remind remind = new Remind();
        remind.setId(Integer.valueOf(txt_id.getText()));
        remind.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        remind.setContent(txt_content.getText());
        remind.setLinkFile(linkAnh);
        remind.setTitle(txt_title.getText());
        remind.setTypeFile(typeFile);
        String datestr = txt_date.getValue().toString() +" "+ txt_time.getText();
        System.out.println(datestr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date parsedDate = dateFormat.parse(datestr);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        remind.setRemindTime(timestamp);
        remindDao.updateRemind(remind);
        Message.getMess("Edit remind successful !!!");
    }
}
