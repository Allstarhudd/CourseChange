module com.justusjoel {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;

    // JavaFX needs this to launch App.java
    exports com.justusjoel;

    // FXML needs reflective access to controllers
    opens com.justusjoel to javafx.fxml;
    opens com.justusjoel.ui to javafx.fxml;
}

