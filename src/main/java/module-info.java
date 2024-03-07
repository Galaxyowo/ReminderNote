module com.team3.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.team3.mavenproject1 to javafx.fxml;
    exports com.team3.mavenproject1;
}
