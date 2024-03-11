module team3.remindernote.App {
    requires javafx.controls;
    requires javafx.fxml;

    opens team3.remindernote to javafx.fxml;
    exports team3.remindernote;
}
