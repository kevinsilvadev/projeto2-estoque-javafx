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
import javafx.scene.control.*;

import java.sql.ResultSet;


public class NewCategoryController  {

    @FXML
    private TextField name;

    @FXML
    private Button btnNewCategory;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnDeletar;

    @FXML
    private TextField deletarCategoria;

    @FXML
    private Button btnListarCategorias;

    @FXML
    private TextField nomeAntigo;

    @FXML
    private TextField nomeNovo;

    @FXML
    private Button btnAtualizarCategoria;


    //Conexão com banco
   final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","emiliobiasi","senha37900");

    ObservableList<Category> list = FXCollections.observableArrayList();
    ObservableList<Product> list2 = FXCollections.observableArrayList();

    public NewCategoryController() throws Exception {
    }

    private void atualizarCategoria() throws Exception {
            int bool = 0;
            findCategories();
            findProduct();
        try {
            for (Product y : list2) {
                if (nomeAntigo.getText().toLowerCase().equals(y.getName_categoria())) {
                    Alerts.showAlert("ERRO", "A CATEGORIA NÃO PODE SER ALTERADA, POIS EXISTEM PRODUTOS ATRELADOS A ELA", null, Alert.AlertType.ERROR);
                    throw new Exception("A CATEGORIA NÃO PODE SER ALTERADA, POIS EXISTEM PRODUTOS ATRELADOS A ELA");
                }
                System.out.println(list2);
            }
            for (Category x : list) {
                if (nomeAntigo.getText().toLowerCase().equals(x.getName())) {
                    for (Category z : list){
                        if (nomeNovo.getText().toLowerCase().equals(z.getName())) {
                            Alerts.showAlert("ERRO", "NÃO FOI POSSÍVEL ATUALIZAR CATEGORIA, POIS VALOR ATUALIZADO JÁ EXISTE", null, Alert.AlertType.ERROR);
                            throw new Exception("NÃO FOI POSSÍVEL ATUALIZAR CATEGORIA");
                        }
                    }
                }
            }
            for (Category w : list){
                if (nomeAntigo.getText().toLowerCase().equals(w.getName())) {
                    c.conect();
                    Category ct1 = null;
                    try {
                        ct1 = new Category(nomeAntigo.getText().toLowerCase());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    Category ct2 = null;
                    try {
                        ct2 = new Category(nomeNovo.getText().toLowerCase());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    c.query("UPDATE CATEGORIA SET NAME = '"+ct2+"' WHERE NAME ='"+ct1+"'");
                    Alerts.showAlert("CATEGORIA ATUALIZADA", "CATEGORIA ATUALIZADA COM SUCESSO", null, Alert.AlertType.INFORMATION);
                    bool = 1;
                }
            }
            if (bool == 0) {
                Alerts.showAlert("ERRO", "NÃO FOI POSSÍVEL ATUALIZAR CATEGORIA, POIS CATEGORIA NÃO EXISTE", null, Alert.AlertType.ERROR);
                throw new Exception("NÃO FOI POSSÍVEL ATUALIZAR CATEGORIA");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void  findProduct() throws Exception {
        c.conect();
        ResultSet rs =  c.query("select * from produtos");
        list2.clear();
        while (rs.next()) {
            list2.addAll(new Product(rs.getString("name"),rs.getDouble("price"), rs.getInt("qtd"), rs.getString("description"),rs.getString("name_categoria")));
        }
    }

    private void  findCategories() throws Exception {
        c.conect();
        ResultSet rs =  c.query("select * from categoria;");
        list.clear();
        while (rs.next()) {
            list.addAll(new Category(rs.getString("name")));
        }
        list.forEach(System.out::println);
    }

    private void newCategory () throws Exception {
        try{
            findCategories();
            list
                    .stream()
                    .forEach(
                            x -> {
                                if(name.getText().toLowerCase().equals(x.getName())){
                                    Alerts.showAlert("ERRO", "A CATEGORIA JÁ EXISTE NA BASE DE DADOS", null, Alert.AlertType.ERROR);
                                    try {
                                        throw new Exception("A CATEGORIA JÁ EXISTE NA BASE DE DADOS");
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                    );
            Category category =  new Category(name.getText());
            c.conect();
            c.query("insert into categoria (name) values ('"+category.getName().toLowerCase()+"')");
            Alerts.showAlert("CATEGORIA INSERIDA", "CATEGORIA CADASTRADA COM SUCESSO", null, Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void  deletarCategoria() throws Exception {
        int bool= 0;
        findCategories();
        findProduct();
        try {
            for (Product y : list2) {
                if (deletarCategoria.getText().toLowerCase().equals(y.getName_categoria())) {
                    Alerts.showAlert("ERRO", "A CATEGORIA NÃO PODE SER DELETADA, POIS EXISTEM PRODUTOS ATRELADOS A ELA", null, Alert.AlertType.ERROR);
                    throw new Exception("A CATEGORIA NÃO PODE SER DELETADA, POIS EXISTEM PRODUTOS ATRELADOS A ELA");
                }
                System.out.println(list2);
            }
            for (Category x : list) {
                if (deletarCategoria.getText().toLowerCase().equals(x.getName())) {
                    c.conect();
                    System.out.println("Name: " + deletarCategoria.getText().toLowerCase());
                    c.query("delete from categoria where name = '" + deletarCategoria.getText().toLowerCase() + "';");
                    c.disconect();
                    Alerts.showAlert("CATEGORIA DELETADA", "CATEGORIA DELETADA COM SUCESSO", null, Alert.AlertType.INFORMATION);
                    bool = 1;
                }
            }
            if (bool == 0) {
                Alerts.showAlert("ERRO", "A CATEGORIA NÃO EXISTE NA BASE DE DADOS", null, Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    public void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }

    @FXML
    public void btnCadastrarOnAction () throws Exception {
        newCategory();
        name.clear();
    }

    @FXML
    public void btnAtualizarCategoriaOnAction() throws Exception {
        atualizarCategoria();
        nomeAntigo.clear();
        nomeNovo.clear();
    }

    @FXML
    public void btnDeletarOnAction () throws Exception {
        deletarCategoria();
        deletarCategoria.clear();
    }

    @FXML
    public void btnAtualizarOnAction() throws Exception {
        StartApplication.changeScreen("listar-categoria-view.fxml");
    }

}