package com.projeto2.demo.projeto2maligno;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class StartApplication extends Application {
    private static Scene telaCadastrarProdutos;
    private static Scene telaNewCategory;
    private static  Scene telaListarCategoria;
    private static Scene telaHome;
    private static Stage stage;
    private static Scene telaTableView;

    @Override
    public void start(Stage stage) throws IOException {

        StartApplication.stage = stage;

        Parent parentTelaHome = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        telaHome = new Scene(parentTelaHome, 830,550);

        Parent parentCadastrarProdutos = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-product-view.fxml")));
        telaCadastrarProdutos = new Scene(parentCadastrarProdutos, 830,550);

        Parent parentNewCategory = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-category-view.fxml")));
        telaNewCategory = new Scene(parentNewCategory, 830,550);

        Parent parentTableView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table-view.fxml")));
        telaTableView = new Scene(parentTableView, 830,550);

        Parent parentListCategoria = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("listar-categoria-view.fxml")));
        telaListarCategoria = new Scene(parentListCategoria, 830,550);

        stage.resizableProperty().setValue(false);
        stage.setScene(telaHome);
        stage.show();
    }

    public static void changeScreen(String src) {
        switch (src) {
            case "home-view.fxml":
                stage.setScene(telaHome);
                break;
            case "new-product-view.fxml":
                stage.setScene(telaCadastrarProdutos);
                break;
            case "new-category-view.fxml":
                stage.setScene(telaNewCategory);
                break;
            case "table-view.fxml":
                stage.setScene(telaTableView);
                break;
            case "listar-categoria-view.fxml":
                stage.setScene(telaListarCategoria);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}