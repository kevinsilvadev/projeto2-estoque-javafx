package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Alerts;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import com.projeto2.demo.projeto2maligno.dbos.Product;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

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

    @FXML
    private TextField nomeAntigo;

    @FXML
    private TextField novoNome;

    @FXML
    private TextField novoPreco;

    @FXML
    private TextField novaQuantidade;

    @FXML
    private TextArea novaDescricao;

    @FXML
    private Button btnAtualizarProduto;

    @FXML
    private ComboBox<Category> comboBoxNovaCategoria;

     List<Category> categoryList = new ArrayList<>();

     ObservableList<Category> categories;

    List<Category> categoryNewList = new ArrayList<>();

    ObservableList<Category> newCategories;

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

    private void atualizarProdutos() throws Exception {
        Category cb = comboBoxNovaCategoria.getSelectionModel().getSelectedItem();
        Product p1 = new Product(novoNome.getText(),Double.valueOf(novoPreco.getText()),
                Integer.valueOf(novaQuantidade.getText()),
                novaDescricao.getText(),
                String.valueOf(cb));
        c.conect();
        c.query("UPDATE PRODUTOS " +
                "SET name = '"+p1.getName()+"', price = "+p1.getPreco()+", qtd = "+ p1.getQtd()+", description = '"+p1.getDescription()+"', name_categoria = '"+p1.getName_categoria()+"' where name = '"+nomeAntigo.getText()+"'");
        c.disconect();
    }

    @FXML
    protected void btnActionAtualizarProdutos(ActionEvent actionEvent) throws Exception {
        atualizarProdutos();
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
                categoryNewList.add(new Category(rs.getString("name")));
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

        newCategories = FXCollections.observableList(categoryNewList);
        comboBoxNovaCategoria.setItems(newCategories);
        c.disconect();
    }
}
