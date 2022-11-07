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
    private Button btnVisualizarProdutos;

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

    @FXML
    private Button btnRecarregarCategorias;

    @FXML
    private Button btnRecarregarCategorias1;
     List<Category> categoryList = new ArrayList<>();

     ObservableList<Category> categories;

    List<Category> categoryNewList = new ArrayList<>();

    ObservableList<Category> newCategories;

    //Conexão com o banco
    final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","emiliobiasi","senha37900");

    ObservableList<Product> list = FXCollections.observableArrayList();

    private void  findProduct() throws Exception {
        c.conect();
        ResultSet rs =  c.query("select * from produtos");
        list.clear();
        while (rs.next()) {
            list.addAll(new Product(rs.getString("name"),rs.getDouble("price"), rs.getInt("qtd"), rs.getString("description"),rs.getString("name_categoria")));
        }
    }
    private void cadastroDeProduto () {
        try {

            if (nome.getText().equals("") || preco.getText().equals("") || quantidade.getText().equals("") || description.getText().equals("") || String.valueOf(comboBox.getSelectionModel().getSelectedItem()).equals("")) {
                Alerts.showAlert("ERRO", "DIGITE CORRETAMENTE NOS CAMPOS E SELECIONE UMA CATEGORIA", null, Alert.AlertType.ERROR);
            }

            findProduct();
            list.stream().forEach(
                    x -> {
                        if (nome.getText().toLowerCase().equals(x.getName())) {
                            Alerts.showAlert("ERRO", "O PRODUTO JÁ EXISTE NA BASE DE DADOS", null, Alert.AlertType.ERROR);
                            try {
                                throw new Exception("O PRODUTO JÁ EXISTE NA BASE DE DADOS");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
            );
            System.out.println(list);
            Category cb = comboBox.getSelectionModel().getSelectedItem();
            Product p =  new Product(nome.getText(), Double.valueOf(preco.getText()), Integer.valueOf(quantidade.getText()),description.getText(), String.valueOf(cb));
            c.conect();
            c.query("insert into produtos (name_categoria, name, price, qtd, description) values ('"+p.getName_categoria()+"', " +
                    "'"+p.getName().toLowerCase()+"', "+p.getPreco()+", "+p.getQtd()+", '"+p.getDescription().toLowerCase()+"')");
            Alerts.showAlert("PRODUTO INSERIDO!", "PRODUTO CADASTRADO COM SUCESSO", null, Alert.AlertType.INFORMATION);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void  deletarProduto() throws Exception {
        try {
            int bool = 0;
            findProduct();
            for (Product x : list) {
                if (name.getText().toLowerCase().equals(x.getName())) {
                    c.conect();
                    System.out.println("Name: " + name.getText().toLowerCase());
                    c.query("delete from produtos where name = '" + name.getText().toLowerCase() + "';");
                    c.disconect();
                    Alerts.showAlert("PRODUTO DELETADO", "PRODUTO DELETADO COM SUCESSO", null, Alert.AlertType.INFORMATION);
                    bool = 1;
                }
            }
            if (bool == 0) {
                Alerts.showAlert("ERRO", "O PRODUTO NÃO EXISTE NA BASE DE DADOS", null, Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void atualizarProdutos() throws Exception {
        try {
            int bool = 0;
            findProduct();
            for (Product x : list) {
                if (nomeAntigo.getText().toLowerCase().equals(x.getName())) {
                    for (Product z : list){
                        if (novoNome.getText().toLowerCase().equals(z.getName()) && !novoNome.getText().toLowerCase().equals(nomeAntigo.getText().toLowerCase())) {
                            Alerts.showAlert("ERRO", "NÃO FOI POSSÍVEL ATUALIZAR PRODUTO, POIS NOME ATUALIZADO JÁ EXISTE", null, Alert.AlertType.ERROR);
                            throw new Exception("NÃO FOI POSSÍVEL ATUALIZAR PRODUTO");
                        }
                    }
                }
            }
            for (Product w : list){
                if (nomeAntigo.getText().toLowerCase().equals(w.getName())) {

                    Category cb = comboBoxNovaCategoria.getSelectionModel().getSelectedItem();
                    Product p1 = new Product(novoNome.getText().toLowerCase(),Double.valueOf(novoPreco.getText()),
                            Integer.valueOf(novaQuantidade.getText()),
                            novaDescricao.getText().toLowerCase(),
                            String.valueOf(cb));
                    c.conect();
                    c.query("UPDATE PRODUTOS " +
                            "SET name = '"+p1.getName().toLowerCase()+"', " +
                            "price = "+p1.getPreco()+", " +
                            "qtd = "+ p1.getQtd()+", " +
                            "description = '"+p1.getDescription().toLowerCase()+"', " +
                            "name_categoria = '"+p1.getName_categoria().toLowerCase()+"' " +
                            "where name = '"+nomeAntigo.getText().toLowerCase()+"'");
                    Alerts.showAlert("PRODUTO ATUALIZADO", "PRODUTO ATUALIZADO COM SUCESSO", null, Alert.AlertType.INFORMATION);
                    c.disconect();
                    bool = 1;
                }
            }
            if (bool == 0) {
                Alerts.showAlert("ERRO", "NÃO FOI POSSÍVEL ATUALIZAR PRODUTO, POIS PRODUTO DIGITADO NÃO EXISTE", null, Alert.AlertType.ERROR);
                throw new Exception("NÃO FOI POSSÍVEL ATUALIZAR PRODUTO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    protected void btnActionAtualizarProdutos(ActionEvent actionEvent) throws Exception {
        atualizarProdutos();
        novoNome.clear();
        nomeAntigo.clear();
        novaDescricao.clear();
        novaQuantidade.clear();
        novoPreco.clear();
    }

    @FXML
    protected void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }

    @FXML
    public void btnActionVisualizarProdutos() {
        StartApplication.changeScreen("table-view.fxml");
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
    public void btnDeletarOnAction() throws Exception {
        deletarProduto();
        name.clear();
    }

    @FXML
    public void btnRecarregarCategoriasOnAction () {
        categoryList.clear();
        categoryNewList.clear();
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
