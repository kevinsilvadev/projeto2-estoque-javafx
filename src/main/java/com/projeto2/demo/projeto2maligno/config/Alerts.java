package com.projeto2.demo.projeto2maligno.config;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Alerts {
    public static void showAlert(String title, String header, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}