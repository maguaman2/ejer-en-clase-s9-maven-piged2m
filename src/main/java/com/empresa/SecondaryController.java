package com.empresa;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {
    @FXML
    private TextField txtMensaje;

    @FXML
    private Label lbMensaje;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToCreditos() throws IOException {
        App.setRoot("creditos");
    }

    @FXML
    private void establecerMensaje() throws IOException {
        txtMensaje.setText("Se pulson un boon");
    }

    @FXML
    private void obtenerMensaje() throws IOException {
        lbMensaje.setText(txtMensaje.getText());

    }


}