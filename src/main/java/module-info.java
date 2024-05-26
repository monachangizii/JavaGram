module com.example.javagram {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.javagram to javafx.fxml;
    exports com.example.javagram;
    exports Model;

    // Open the package containing the Model class to JavaFX
    opens Model to javafx.base, javafx.fxml;


}