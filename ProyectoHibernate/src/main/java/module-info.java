module org.example.proyectohibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens org.example.proyectohibernate to javafx.fxml;
    exports org.example.proyectohibernate;
}