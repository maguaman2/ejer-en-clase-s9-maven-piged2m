module com.empresa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.empresa to javafx.fxml;
    exports com.empresa;
}