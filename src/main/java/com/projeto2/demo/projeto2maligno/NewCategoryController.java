package com.projeto2.demo.projeto2maligno;

import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.entities.Category;
import com.projeto2.demo.projeto2maligno.entities.Products;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCategoryController {

    @FXML
    private TextField name;

    @FXML
    private Button btnNewCategory;


    private void newCategory () {

        try {
            Category category =  new Category(name.getText());
            Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","postgres","kevin");
            c.conect();
            c.query("insert into categoria (name) values ('"+category.getName()+"')");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

}
    @FXML
    public void btnCadastrarOnAction () {
        newCategory();
    }
}