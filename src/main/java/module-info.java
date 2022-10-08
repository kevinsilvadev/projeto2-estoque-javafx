module com.projeto2.demo.projeto2maligno {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;

    opens com.projeto2.demo.projeto2maligno to javafx.fxml;
    exports com.projeto2.demo.projeto2maligno;
}