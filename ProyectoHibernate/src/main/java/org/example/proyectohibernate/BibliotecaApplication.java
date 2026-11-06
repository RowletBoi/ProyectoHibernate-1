package org.example.proyectohibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyectohibernate.controller.LibroController;

import java.io.IOException;

public class BibliotecaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BibliotecaApplication.class.getResource("/org/example/proyectohibernate/libros-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ggggg!");
        stage.setScene(scene);
        stage.show();
    }
}
