module uit.ac.ma.blockchaincybersecurity {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens uit.ac.ma.blockchaincybersecurity to javafx.fxml;
    exports uit.ac.ma.blockchaincybersecurity;
}