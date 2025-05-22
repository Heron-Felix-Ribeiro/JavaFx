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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCodigo;

    @FXML
    private Label menssagemText;

    @FXML
    public void OnClickBtnSalvar (ActionEvent event) {
        EntityManager em = JPAUtils.getEntityManager();

        UsuarioDAO dao = new UsuarioDAO(em);
        Usuario usuario = new Usuario();

        usuario.setUsuario(txtNome.getText());
        usuario.setEmail(txtEmail.getText());

        dao.salvar(usuario);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Salvo com sucesso!");
        alert.showAndWait();

        txtNome.clear();
        txtEmail.clear();
        txtCodigo.clear();
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
