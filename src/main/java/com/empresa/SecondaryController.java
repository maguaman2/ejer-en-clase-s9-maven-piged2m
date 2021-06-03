package com.empresa;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEstado;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToCreditos() throws IOException {
        App.setRoot("creditos");
    }

    @FXML
    private void crerCliente() throws IOException {
        //crear  objeto Client con info de la vista
        Client client = new Client(txtNombre.getText(),
                txtDireccion.getText(),txtTelefono.getText(),
                txtEmail.getText(),Boolean.parseBoolean(txtEstado.getText()));

        ClientDao clientDao = new ClientDao();
        clientDao.crearClient(client);
        

    }


}