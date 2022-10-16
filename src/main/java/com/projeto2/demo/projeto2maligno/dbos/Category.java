package com.projeto2.demo.projeto2maligno.dbos;

import com.projeto2.demo.projeto2maligno.config.Alerts;
import javafx.scene.control.Alert;


public class Category {

    private String name;

    public Category() {
    }

    public Category(String name) throws Exception {
        if(name.isBlank() || name.isEmpty()) {
            Alerts.showAlert("ERROR", "NOME DA CATEGORIA INVÁLIDA", null, Alert.AlertType.ERROR);
            throw new Exception("Nome da categoria inválida");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int ret = 7;
        if (this.name != null) ret = 7 * ret + this.name.hashCode();
        if (ret < 0) ret=-ret;
        return ret;
    }

    @Override
    public String toString() {
        return getName();
    }
}
