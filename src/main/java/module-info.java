module com.projeto2.demo.projeto2maligno {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.projeto2.demo.projeto2maligno to javafx.fxml;
    exports com.projeto2.demo.projeto2maligno;
}