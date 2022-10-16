package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Alerts;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import com.projeto2.demo.projeto2maligno.dbos.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public  class NewProdutosController implements Initializable {

    @FXML
    private TextField nome;

    @FXML
    private TextField preco;

    @FXML
    private TextField quantidade;

    @FXML
    private TextArea description;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnNewCategory;

    @FXML
    private Button btnDeletarProduto;

    @FXML
    private TextField name;

    @FXML
    private  ComboBox<Category> comboBox;

     List<Category> categoryList = new ArrayList<>();

     ObservableList<Category> categories;


    //Conexão com o banco
    final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","postgres","kevin");


    private void cadastroDeProduto () {
        try {
            Product p =  new Product(nome.getText(), Double.valueOf(preco.getText()), Integer.valueOf(quantidade.getText()),description.getText(), null);
            c.conect();
            Category cb = comboBox.getSelectionModel().getSelectedItem();
            c.query("insert into produtos (name_categoria, name, price, qtd, description) values ('"+cb+"', " +
                    "'"+p.getName()+"', "+p.getPreco()+", "+p.getQtd()+", '"+p.getDescription()+"')");
            Alerts.showAlert("SUCESS", "PRODUTO CADASTRADO COM SUCESSO", null, Alert.AlertType.INFORMATION);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void  deletarProduto()  {
        c.conect();
        System.out.println("Name: " + name.getText());
        c.query("delete from produtos where name = '"+name.getText()+"';");
        c.disconect();
        Alerts.showAlert("PRODUTO DELETADO", "PRODUTO DELETADO COM SUCESSO",null, Alert.AlertType.INFORMATION);
    }

    private void atualizarProdutos() {
        //code
    }


    @FXML
    protected void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }

    @FXML
    protected void btnActionNovaCategoria(ActionEvent actionEvent) {
        StartApplication.changeScreen("new-category-view.fxml");
    }

    @FXML
    public void btnCadastrarOnAction() {
        cadastroDeProduto();
        nome.clear();
        quantidade.clear();
        preco.clear();
        description.clear();
    }

    @FXML
    public void btnDeletarOnAction() {
        deletarProduto();
        name.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c.conect();
        ResultSet rs =  c.query("select * from categoria");

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                categoryList.add(new Category(rs.getString("name")));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        categories = FXCollections.observableList(categoryList);
        comboBox.setItems(categories);
        c.disconect();
    }
}
