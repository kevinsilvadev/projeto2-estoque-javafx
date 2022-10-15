package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.config.Alerts;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCategoryController {

    @FXML
    private TextField name;

    @FXML
    private Button btnNewCategory;


    private boolean isValid(Category c) throws Exception {
        if(c.getName() == "" && c.getName() == null) {
            Alerts.showAlert("ERROR", "ERROR", null, Alert.AlertType.ERROR);
            new Exception("ERROR");
        }
        return false;
    }

    //Conexão com banco
   final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","postgres","kevin");
    private void newCategory () throws Exception {
        try {
            Category category =  new Category(name.getText());
            c.conect();
            c.query("insert into categoria (name) values ('"+category.getName()+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void btnCadastrarOnAction () throws Exception {
        newCategory();
    }
}