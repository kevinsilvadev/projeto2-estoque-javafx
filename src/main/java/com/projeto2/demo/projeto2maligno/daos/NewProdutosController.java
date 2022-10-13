package com.projeto2.demo.projeto2maligno.daos;

import com.projeto2.demo.projeto2maligno.config.Connection;
import com.projeto2.demo.projeto2maligno.dbos.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewProdutosController {

    @FXML
    private TextField nome;

    @FXML
    private TextField preco;

    @FXML
    private TextField quantidade;

    @FXML
    private TextArea description;

    private void cadastroDeProduto () {

        try {
            Product p =  new Product(nome.getText(), Integer.valueOf(preco.getText()), Integer.valueOf(quantidade.getText()),description.getText());
            Connection c = new Connection("PostgreSql","localhost","5432","projeto2-estoque","postgres","kevin");
            c.conect();
            c.query("insert into produtos (id_categorias, name, price, qtd, description) values (1, " +
                    "'"+p.getName()+"', "+Integer.valueOf(p.getPreco())+", "+Integer.valueOf(p.getQtd())+", '"+p.getDescription()+"')");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void btnCadastrarOnAction() {
        cadastroDeProduto();
    }


}
