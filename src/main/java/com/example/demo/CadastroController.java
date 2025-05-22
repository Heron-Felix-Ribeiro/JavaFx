package com.example.demo;

import com.example.demo.model.DAO.UsuarioDAO;
import com.example.demo.model.Usuario;
import com.example.demo.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtCep;

    @FXML
    private ComboBox<String> cbEstados;

    @FXML
    private ComboBox<String> cbCidades;

    @FXML
    private ComboBox<String> cbBairros;

    @FXML
    private ComboBox<String> cbRua;

    @FXML TextField txtComplemento;


    private void carregarEstados() {
        cbEstados.getItems().addAll();
    }

    private void carregarCidades() {
        cbCidades.getItems().addAll();
    }

    private void carregarBairros() {
        cbBairros.getItems().addAll();
    }

    private void carregarRua() {
        cbRua.getItems().addAll();
    }


    @FXML
    private Label menssagemText;


    @FXML
    public void OnClickBtnSalvar (ActionEvent event) {
        try {
            URL url = new URL("https://localhost:8080/usuario");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String jsonInputString = "{\"usuario\": \"" + txtNome.getText() + "\", " +
                    "\"email\": \"" + txtEmail.getText() + "\"," +
                    " \"idade\": " + txtIdade.getText() + "," +
                    " \"senha\": \"" + txtSenha.getText() + "\"," +
                    " \"cep\": \"" + txtCep.getText() + "\"," +
                    " \"estado\": \"" + cbEstados.getValue() + "\"," +
                    " \"cidade\": \"" + cbCidades.getValue() + "\"," +
                    " \"bairro\": \"" + cbBairros.getValue() + "\"," +
                    " \"rua\": \"" + cbRua.getValue() + "\"," +
                    " \"complemento\": \"" + txtComplemento.getText() + "\"}";
            try(OutputStream os = conn.getOutputStream()) {
                os.write(jsonInputString.getBytes(), 0, jsonInputString.length());
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
                new Alert(Alert.AlertType.INFORMATION, json).showAndWait();
            }
//            EntityManager em = JPAUtils.getEntityManager();
//
//            UsuarioDAO dao = new UsuarioDAO(em);
//            Usuario usuario = new Usuario();
//
//            usuario.setUsuario(txtNome.getText());
//            usuario.setEmail(txtEmail.getText());
//            usuario.setIdade(Integer.parseInt(txtIdade.getText()));
//            usuario.setSenha(txtSenha.getText());
//            usuario.setCep(txtCep.getText());
//            usuario.setEstado(cbEstados.getValue());
//            usuario.setCidade(cbCidades.getValue());
//            usuario.setBairro(cbBairros.getValue());
//            usuario.setRua(cbRua.getValue());
//            usuario.setComplemento(txtComplemento.getText());
//
//            dao.salvar(usuario);
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salvo com sucesso!");
//            alert.showAndWait();
//
//            txtNome.clear();
//            txtEmail.clear();
//            txtCodigo.clear();
        } catch (Exception e) {
            e.printStackTrace();
            menssagemText.setText("Erro ao salvar o usuário.");
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
