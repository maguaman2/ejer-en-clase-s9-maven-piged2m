package com.empresa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    List<Persona> lista = new ArrayList();

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private void switchToSecondary() throws IOException {

        App.setRoot("secondary");
    }

    @FXML
    private void botonAgregar() throws IOException {

        //int codigo = Integer.parseInt(txtCode.getText());
        try {
            Persona persona = new Persona(Integer.parseInt(txtCode.getText()), txtName.getText());
            lista.add(persona);
        }catch (Exception e){
            System.out.println("El codigo debe ser un numero");

        }

    }

    @FXML
    private void imprimirColeccion() throws IOException {

        //for mejorado
        for (Object elemento: lista){
            Persona persona = (Persona) elemento; // casteo
            System.out.println(persona.getId()+" "+persona.getNombre());
        }

    }

    @FXML
    private void archivos() throws IOException {
        GestionArchivos.crearArchivo("C:\\archivos\\prueba2.txt");
        List<String> credenciales = new ArrayList<>();
        credenciales = GestionArchivos.leerArchivo("C:\\archivos\\credenciales.txt");

        for (String elemento: credenciales){
            System.out.println(elemento);
        }
    }

}
