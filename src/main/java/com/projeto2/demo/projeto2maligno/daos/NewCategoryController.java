package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.StartApplication;
import com.projeto2.demo.projeto2maligno.config.Alerts;
import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;



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


    public NewCategoryController() throws Exception {
    }

    private void atualizarCategoria() throws Exception {
        /*
        if (nomeNovo.getText() == nomeDoObjetoDaLista) {
            Alert("JA TEM CATEGORIA DESSE TIPO AI NO BANCO, MAGRELO!");
        } else {
        */
        c.conect();
        Category ct1 = new Category(nomeAntigo.getText());
        Category ct2 = new Category(nomeNovo.getText());
        c.query("UPDATE CATEGORIA SET NAME = '"+ct2+"' WHERE NAME ='"+ct1+"'");
    }

    private void newCategory () throws Exception {

        /*
        if (nome.getText() == nomeDoObjetoDaLista) {
            Alert("JA TEM CATEGORIA DESSE TIPO AI NO BANCO, MAGRELO!");
        } else {
        */
        Category category =  new Category(name.getText());
        c.conect();
        c.query("insert into categoria (name) values ('"+category.getName()+"')");

    }


    private void  deletarCategoria() {
        c.conect();
        System.out.println("Name: " + deletarCategoria.getText());
        c.query("delete from categoria where name = '"+deletarCategoria.getText()+"';");
        c.disconect();
        Alerts.showAlert("CATEGORIA DELETADO", "CATEGORIA DELETADO COM SUCESSO",null, Alert.AlertType.INFORMATION);
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
        name.clear();
    }

    @FXML
    public void btnDeletarOnAction () throws Exception {
        deletarCategoria();
        name.clear();
    }

    @FXML
    public void btnAtualizarOnAction() throws Exception {
        StartApplication.changeScreen("listar-categoria-view.fxml");
    }

}