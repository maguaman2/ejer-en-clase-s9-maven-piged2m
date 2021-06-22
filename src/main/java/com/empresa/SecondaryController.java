package com.empresa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private CheckBox chkEstado;

    @FXML
    private TextField txtBusqueda;

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

    Client client;
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
        System.out.println(txtEmail.getText().matches("^[a-z]+[0-9]*[@]{1}[a-z]+([.][a-z]+)+$"));

/*
        //crear  objeto Client con info de la vista
        Client client = new Client(txtNombre.getText(),
                txtDireccion.getText(),txtTelefono.getText(),
                txtEmail.getText(),chkEstado.isSelected());

        ClientDao clientDao = new ClientDao();
        clientDao.crearClient(client);
        cargarTableView();

*/
    }

    @FXML
    private void actualizarCliente() throws IOException {
        Client client = new Client(Integer.parseInt(txtId.getText()), txtNombre.getText(),
                txtDireccion.getText(),txtTelefono.getText(),
                txtEmail.getText(),chkEstado.isSelected());

        ClientDao clientDao = new ClientDao();
        clientDao.actualizarCliente(client);
        cargarTableView();
    }

    @FXML
    private void eliminarCliente() throws IOException {
        ClientDao clientDao = new ClientDao();
        int id = Integer.parseInt(txtId.getText());
        clientDao.eliminarCliente(id);
        cargarTableView();

        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtEstado.setText("");
    }

    @FXML   // anotacion
    private void cargarBusqueda(){
        tbClientes.getItems().clear();

        ClientDao clienteDao = new ClientDao();
        for (Client cliente : clienteDao.buscar(txtBusqueda.getText())) {
            obsList.add(cliente);
        }
        tbClientes.setItems(obsList);
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

    @FXML
    private void onClick1(MouseEvent event) {
        ObservableList<Client> row;
        row=tbClientes.getSelectionModel().getSelectedItems();
        if (tbClientes.getSelectionModel().getSelectedItems().size() > 0){
            client =  new Client(
                    row.get(0).getId(),
                    row.get(0).getNombre(),
                    row.get(0).getDireccion(),
                    row.get(0).getTelefono(),
                    row.get(0).getEmail(),
                    row.get(0).isEstado()
            );
            //System.out.println(client.getNombre());
            txtId.setText(String.valueOf(client.getId()));
            txtNombre.setText(client.getNombre());
            txtDireccion.setText(client.getDireccion());
            txtTelefono.setText(client.getTelefono());
            txtEmail.setText(client.getEmail());
            chkEstado.setSelected(client.isEstado());

        }
    }

}