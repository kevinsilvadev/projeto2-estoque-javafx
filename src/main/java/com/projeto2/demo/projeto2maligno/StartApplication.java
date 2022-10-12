package com.projeto2.demo.projeto2maligno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartApplication extends Application {

    private static Scene telaTableView;
    private static Scene telaCadastrarProdutos;

    private static Scene telaNewCategory;
    private static Scene telaHome;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        StartApplication.stage = stage;

        Parent parentTelaHome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        telaHome = new Scene(parentTelaHome, 640,400);

        Parent parentCadastrarProdutos = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-product-view.fxml")));
        telaCadastrarProdutos = new Scene(parentCadastrarProdutos, 640,400);

        Parent parentTableView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table-view.fxml")));
        telaTableView = new Scene(parentTableView, 640,400);

        Parent parentNewCategory = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-category-view.fxml")));
        telaNewCategory = new Scene(parentNewCategory, 640,400);

        stage.setScene(telaHome);
        stage.show();
    }

    public static void changeScreen(String src) {
        switch (src) {
            case "home-view.fxml":
                stage.setScene(telaHome);
                break;
            case "table-view":
                stage.setScene(telaTableView);
                break;
            case "new-product-view.fxml":
                stage.setScene(telaCadastrarProdutos);
                break;
            case "new-category-view.fxml":
                stage.setScene(telaNewCategory);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}