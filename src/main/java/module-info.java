module com.example.ruchirap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ruchirap to javafx.fxml;
    exports com.example.ruchirap;
}