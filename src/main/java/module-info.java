module com.example.binarysearchtree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.binarysearchtree to javafx.fxml;
    exports com.example.binarysearchtree;
}