module team3.remindernote.App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens team3.remindernote.controller to javafx.fxml;
    opens team3.remindernote to javafx.fxml;
    exports team3.remindernote;
    exports team3.remindernote.controller;
}
