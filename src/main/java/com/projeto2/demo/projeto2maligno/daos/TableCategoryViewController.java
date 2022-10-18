package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import com.projeto2.demo.projeto2maligno.dbos.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TableCategoryViewController implements Initializable {

    @FXML
    private TableView<Category> tableCategoria;

    @FXML
    private TableColumn<Category, String> name;

    @FXML
    private Button btnAtualizarCategoria;

    final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","emiliobiasi","senha37900");


    public TableCategoryViewController() throws Exception {
    }

    ObservableList<Category> list = FXCollections.observableArrayList();


    private void  findCategories() throws Exception {
        c.conect();
        ResultSet rs =  c.query("select * from categoria;");
        list.clear();
        while (rs.next()) {
            list.addAll(new Category(rs.getString("name")));
        }
        list.forEach(System.out::println);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.stream().forEach(System.out::println);
        name.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        tableCategoria.setItems(list);
    }

    @FXML
    public void btnAtualizarOnAction() throws Exception {
        findCategories();
    }
    @FXML
    public void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }

    @FXML
    public void btnListarCategorias(ActionEvent actionEvent) {
        StartApplication.changeScreen("listar-categorias-view.fxml");
    }




}
