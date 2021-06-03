module com.empresa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.empresa to javafx.fxml;
    exports com.empresa;
}