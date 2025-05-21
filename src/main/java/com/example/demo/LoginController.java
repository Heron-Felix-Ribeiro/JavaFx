package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            URL url = new URL("https://localhost:8080/api/login");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonUsuario = "{\"usuario\": \"" + txtUsuario.getText() + "\", \"password\": \"" + txtSenha.getText() + "\"}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonUsuario.getBytes());
            }

            int resposta = conn.getResponseCode();
            if (resposta == 200){

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                br.close();
                String json = sb.toString();
                new Alert(Alert.AlertType.INFORMATION, json).show();

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