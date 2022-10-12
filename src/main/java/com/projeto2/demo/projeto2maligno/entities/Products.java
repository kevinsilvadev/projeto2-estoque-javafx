package com.projeto2.demo.projeto2maligno.entities;

import java.util.Objects;

public class Products {

    private int id_produto;

    private int id_categoria;
    private String name;
    private Integer preco;
    private Integer qtd;

    private String description;

    public Products(){
    }

    public Products(String name, Integer preco, Integer qtd, String description) {
        this.name = name;
        this.preco = preco;
        this.qtd = qtd;
        this.description = description;

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

    public Integer getPreco() {
        return preco;
    }


    public void setPreco(Integer preco) {
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
        Products products = (Products) o;
        return id_produto == products.id_produto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produto);
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
