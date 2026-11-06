package org.example.proyectohibernate.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectohibernate.dao.LibroDAO;
import org.example.proyectohibernate.model.Libro;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LibroController {

    @FXML
    public TextField txtTitulo;
    @FXML
    public TextField txtAutor;
    @FXML
    public TextField txtAnio;
    @FXML
    public TextField txtPrecio;
    @FXML
    public Button btnGuardar;
    @FXML
    public Button btnCargar;
    @FXML
    public TableView<Libro> tblLibros;
    @FXML
    public TableColumn<Libro, Integer> colID;
    @FXML
    public TableColumn<Libro,String> colTitulo;
    @FXML
    public TableColumn<Libro,String> colAutor;
    @FXML
    public TableColumn<Libro,Integer> colAnio;
    @FXML
    public TableColumn<Libro,Double> colPrecio;
    @FXML
    public Button btnLimpiar;
    @FXML
    public Button btnModificar;
    @FXML
    public Button btnEliminar;


    private final LibroDAO libroDAO = new LibroDAO();


    private Long idSeleccionado = null;

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio_publicacion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tblLibros.getSelectionModel().selectedItemProperty().addListener((
                obs, oldValue, newValue) -> {
            if (newValue != null) {
                selectLibro(newValue);
            } else {
                idSeleccionado = null;
                actualizarBotones();
            }
        });
        cargarLibros();
        actualizarBotones();
    }

    private void selectLibro(Libro libro) {
        idSeleccionado = libro.getId();
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());
        txtAnio.setText(String.valueOf(libro.getAnio_publicacion()));
        txtPrecio.setText(String.valueOf(libro.getPrecio()));
        actualizarBotones();

    }

    private void actualizarBotones() {
        boolean sinSeleccion = idSeleccionado == null;
        btnModificar.setDisable(sinSeleccion);
        btnEliminar.setDisable(sinSeleccion);
    }

    private Libro obtenerLibroFormulario(){
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int anio = Integer.valueOf(txtAnio.getText());
        BigDecimal precio = new BigDecimal(txtPrecio.getText());
        return new Libro(titulo, autor, anio, precio);
    }

    @FXML
    private void guardarLibro(){
        try{
            Libro libro = obtenerLibroFormulario();
            libroDAO.guardar(libro);
            limpiarCampo();
            cargarLibros();
        }catch(Exception ex){
            ex.printStackTrace();

        }
    }
    @FXML
    public void modificarLibro() {
        try{
            Libro libro = obtenerLibroFormulario();
            libro.setId(idSeleccionado);

            boolean modificado = libroDAO.modificar(libro);

            if(!modificado){
                //alert para indicar que hubo un error a nivel de transaccion
            }
            limpiarCampo();
            cargarLibros();

            //Alert parar indiciar que la modificacion fue exitosa

        }catch (Exception ex ){
            ex.printStackTrace();
        }
    }


    public void eliminarLibro() {
        try{
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmacion de eliminacion de datos");
            confirmacion.setHeaderText("Eliminar Libro");
            confirmacion.setContentText("Esta seguro que desea eliminar el registro seleccionado?");

            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.isEmpty() || resultado.get() != ButtonType.OK){
                return;
            }

            boolean eliminado = libroDAO.eliminar(idSeleccionado);
            if(!eliminado){
                //Alert
            }
            //Alert parr indicar que la operaicon fue realizada

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void cargarLibros() {
        try{
            List<Libro> libros = libroDAO.listar();
            limpiarCampo();
            tblLibros.setItems(FXCollections.observableArrayList(libros));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void limpiarCampo() {
        tblLibros.getSelectionModel().clearSelection();
        idSeleccionado = null;
        txtTitulo.clear();
        txtAutor.clear();
        txtAnio.clear();
        txtPrecio.clear();
    }
}
