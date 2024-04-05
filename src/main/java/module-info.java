module team3.remindernote.App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens team3.remindernote to javafx.fxml;
    opens team3.remindernote.controller;
    exports team3.remindernote;
    exports team3.remindernote.controller;
}
