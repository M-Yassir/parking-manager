module com.example.demo5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo5 to javafx.fxml;
    exports com.example.demo5;
}