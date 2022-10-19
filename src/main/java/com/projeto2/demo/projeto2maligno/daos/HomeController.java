package com.projeto2.demo.projeto2maligno.daos;
import com.projeto2.demo.projeto2maligno.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button btnNewProduct;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnConsultarCategorias;

    @FXML
    private Button btnNewCategory;
    @FXML
    public void btnActionNewProduct(ActionEvent actionEvent) throws Exception {
        StartApplication.changeScreen("new-product-view.fxml");

    }

    @FXML
    public void btnActionNewCategory(ActionEvent actionEvent) {
        StartApplication.changeScreen("new-category-view.fxml");
    }

    @FXML
    public void btnActionConsultar(ActionEvent actionEvent) throws Exception {
        StartApplication.changeScreen("table-view.fxml");
    }

    @FXML
    public void btnActionConsultarCategorias(ActionEvent actionEvent) {
        StartApplication.changeScreen("listar-categoria-view.fxml");
    }

}
