package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField cpf;
    private String  txtTipoUsuario = "ADMINISTRADOR";
    @FXML
    private DatePicker txtDataNascimento;

    @FXML
    private Label menssagemText;

    @FXML
    public void OnClickBtnSalvar (ActionEvent event) {
        try {
            URL url = new URL("http://localhost:8080/usuario");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonUsuario = "{\"usuario\": \"" + txtNome.getText() + "\"," +
                    " \"email\": \"" + txtEmail.getText() + "\"," +
                    " \"senha\": \"" + txtSenha.getText() + "\"," +
                    " \"cpf\": \"" + cpf.getText() + "\"," +
                    " \"tipoUsuario\": \"" + txtTipoUsuario + "\"," +
                    " \"dataNascimento\": \"" + txtDataNascimento.getValue() + "\"}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonUsuario.getBytes());
            }

            int resposta = conn.getResponseCode();
            if (resposta == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                br.close();
                String json = sb.toString();
                new Alert(Alert.AlertType.INFORMATION, "Cadastro realizado com sucesso!").show();

            } else {
                menssagemText.setText("Erro ao realizar o cadastro!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            menssagemText.setText("Erro ao conectar com o servidor.");
        }
    }

    @FXML
    public void OnClickBtnVoltar (ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/menu.view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
            menssagemText.setText("Erro ao carregar a tela de menu.");
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
