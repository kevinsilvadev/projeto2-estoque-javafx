package com.projeto2.demo.projeto2maligno.dbos;

import com.projeto2.demo.projeto2maligno.config.Alerts;
import javafx.scene.control.Alert;

import java.util.Objects;

public class Product {

    private int id_produto;

    private String name;
    private Double preco;
    private Integer qtd;
    private String description;
    private String name_categoria;

    public Product(){
    }

    public Product(String name, Double preco, Integer qtd, String description, String name_categoria) throws Exception {

       if(!isValid(name, preco, qtd, description)) {
           throw new Exception("VALORES INVÁLIDOS");
       }

        this.name = name;
        this.preco = preco;
        this.qtd = qtd;
        this.description = description;
        this.name_categoria = name_categoria;


    }

    public boolean isValid(String name, Double preco, Integer qtd, String description) throws Exception {
        if(name.isEmpty() || description.isEmpty()) {
            Alerts.showAlert("PRODUTO INVÁLIDO", "ERRO AO CADASTRAR PRODUTO", null, Alert.AlertType.ERROR);
            throw new Exception("Valor dos campos inválidos");
        }
        if( preco.equals(0) || preco.equals("") || qtd.equals(0) || qtd.equals("") ) {
            Alerts.showAlert("PRODUTO INVÁLIDO", "ERRO AO CADASTRAR PRODUTO", null, Alert.AlertType.ERROR);
            throw new Exception("Valor dos campos inválidos");
        }
        return true;
    }


    public String getName_categoria() {
        return name_categoria;
    }

    public void setName_categoria(String name_categoria) {
        this.name_categoria = name_categoria;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPreco() {
        return preco;
    }


    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQttd(Integer qttd) {
        this.qtd = qttd;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id_produto == product.id_produto;
    }

    @Override
    public int hashCode() {
        int ret = 7;
        if (this.name != null) ret = 7 * ret + this.name.hashCode();
        if (this.preco != null) ret = 7 * ret + this.preco.hashCode();
        if (this.qtd != null) ret = 7 * ret + this.qtd.hashCode();
        if (this.description != null) ret = 7 * ret + this.description.hashCode();
        if (ret < 0) ret=-ret;
        return ret;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id_produto=" + id_produto +
                ", name='" + name + '\'' +
                ", preco=" + preco +
                ", qtd=" + qtd +
                '}';
    }
}
