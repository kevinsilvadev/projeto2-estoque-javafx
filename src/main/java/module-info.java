module com.projeto2.demo.projeto2maligno {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.projeto2.demo.projeto2maligno to javafx.fxml;
    exports com.projeto2.demo.projeto2maligno;
    exports com.projeto2.demo.projeto2maligno.config;
    opens com.projeto2.demo.projeto2maligno.config to javafx.fxml;
    exports com.projeto2.demo.projeto2maligno.dbos;
    opens com.projeto2.demo.projeto2maligno.dbos to javafx.fxml;
    exports com.projeto2.demo.projeto2maligno.daos;
    opens com.projeto2.demo.projeto2maligno.daos to javafx.fxml;
}