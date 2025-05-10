package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Label menssagemText;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {

        try {

            if (txtUsuario.getText().equals("admin") && txtSenha.getText().equals("admin")){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "/com/example/demo/menu.view.fxml"));
                Scene menuScene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(menuScene);
            } else {
                menssagemText.setText("Usuário ou senha inválidos!");
            }

        } catch (Exception e){
            e.printStackTrace();
            menssagemText.setText("Erro ao carregar a tela de menu.");
        }
    }


}