package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.controlsfx.control.action.Action;

public class HomeController {

    @FXML
    private Button btnNewProduct;

    @FXML
    private Button btnNewCategory;
    @FXML
    public void btnActionNewProduct(ActionEvent actionEvent) {
        StartApplication.changeScreen("new-product-view.fxml");
    }

    @FXML
    public void btnActionNewCategory(ActionEvent actionEvent) {
        StartApplication.changeScreen("new-category-view.fxml");
    }
}
