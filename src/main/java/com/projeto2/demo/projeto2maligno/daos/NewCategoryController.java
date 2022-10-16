package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCategoryController {

    @FXML
    private TextField name;

    @FXML
    private Button btnNewCategory;

    @FXML
    private Button btnVoltar;


    //Conexão com banco
   final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","postgres","kevin");

    private void newCategory () throws Exception {
            Category category =  new Category(name.getText());
            c.conect();
            c.query("insert into categoria (name) values ('"+category.getName()+"')");
    }

    @FXML
    public void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }

    @FXML
    public void btnCadastrarOnAction () throws Exception {
        newCategory();
    }
}