package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private void onMenuButtonClick(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/cadastro-view.fxml"));
            Scene cadastroScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(cadastroScene);


        } catch (Exception e) {
            e.printStackTrace();
            menssagemText.setText("Erro ao carregar a tela de cadastro.");
        }
    }

    @FXML
    private void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    private Label menssagemText;

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/login-view.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);


        } catch (Exception e) {
            e.printStackTrace();
            menssagemText.setText("Erro ao carregar a tela de login.");
        }
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
