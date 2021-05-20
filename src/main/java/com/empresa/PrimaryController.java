package com.empresa;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML //anotacion
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML //anotacion
    private void imprimeMensaje() throws IOException {
        System.out.println("Fue pulsado el boton ");
    }


}
