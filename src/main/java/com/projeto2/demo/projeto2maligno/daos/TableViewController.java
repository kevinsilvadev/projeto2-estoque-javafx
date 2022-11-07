package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public  class TableViewController implements Initializable {

    @FXML
    private TextField nome;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConsultar;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> name;

    @FXML
    private TableColumn<Product, Double> preco;

    @FXML
    private TableColumn<Product, Integer> qtd;

    @FXML
    private TableColumn<Product, String> description;

    @FXML
    private TableColumn<Product, String> categoria;

    @FXML
    private Button btnAtualizar;

    ObservableList<Product> list = FXCollections.observableArrayList();


    //Conexão com o banco
    final Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","emiliobiasi","senha37900");

    public TableViewController() throws Exception {
    }

    private void  findProduct() throws Exception {
        c.conect();
        ResultSet rs = c.query("select * from produtos");
        list.clear();
        while (rs.next()) {
            list.addAll(new Product(rs.getString("name"),rs.getDouble("price"), rs.getInt("qtd"), rs.getString("description"),rs.getString("name_categoria")));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        preco.setCellValueFactory(new PropertyValueFactory<Product, Double>("preco"));
        qtd.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qtd"));
        description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        categoria.setCellValueFactory(new PropertyValueFactory<Product, String>("name_categoria"));
        table.setItems(list);
    }

    @FXML
    public void btnConsultarOnAction() throws Exception{
    }

    @FXML
    public void btnAtualizarOnAction() throws Exception {
        findProduct();
    }

    @FXML
    public void btnActionBack(ActionEvent actionEvent) {
        StartApplication.changeScreen("home-view.fxml");
    }
}
