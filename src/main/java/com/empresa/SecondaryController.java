package com.empresa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController implements Initializable {
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
    private TableView<Client> tbClientes;

    @FXML
    private TableColumn<Client, Integer> colId;

    @FXML
    private TableColumn<Client, String> colNombre;

    @FXML
    private TableColumn<Client, String> colDireccion;

    @FXML
    private TableColumn<Client, String> colTelefono;

    @FXML
    private TableColumn<Client, String> colEmail;

    @FXML
    private TableColumn<Client, Boolean> colEstado;

    ObservableList<Client> obsList = FXCollections.observableArrayList();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToCreditos() throws IOException {
        App.setRoot("creditos");
    }

    @FXML
    private void crearCliente() throws IOException {
        //crear  objeto Client con info de la vista
        Client client = new Client(txtNombre.getText(),
                txtDireccion.getText(),txtTelefono.getText(),
                txtEmail.getText(),Boolean.parseBoolean(txtEstado.getText()));

        ClientDao clientDao = new ClientDao();
        clientDao.crearClient(client);
        cargarTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Esto se ejecuta al arrancar");
        colId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Client, String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Client, String>("direccion"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Client, String>("telefono"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Client, Boolean>("estado"));

        cargarTableView();

    }

    private void cargarTableView(){
        tbClientes.getItems().clear();
        ClientDao clienteDao = new ClientDao();
        for (Client cliente : clienteDao.listar()) {
            obsList.add(cliente);
        }
        tbClientes.setItems(obsList);
    }
}