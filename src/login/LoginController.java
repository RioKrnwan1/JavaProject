package login;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField codeSecurity;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label Notif;

    @FXML
    void btnLogin(ActionEvent event) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        if (username.getText().isEmpty() || 
            password.getText().isEmpty() || 
            codeSecurity.getText().isEmpty()) {
            tampilNotif("Form tidak boleh kosong!");
            return;
        }

        if (!codeSecurity.getText().equals("Rayo1")) {
            tampilNotif("Kode security salah!");
            return;
        }

        try (Connection connection = DatabaseConnection.connect()) {
            PreparedStatement login = connection.prepareStatement(query);
            login.setString(1, username.getText());
            login.setString(2, password.getText());

            ResultSet resultSet = login.executeQuery();

            if (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard/dashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.setWidth(800);  
                stage.setHeight(600); 
                stage.show();
            } else {
                tampilNotif("Username atau Password salah!");
            }

        } catch (SQLException e) {
            tampilNotif("Gagal koneksi ke database!");
        } catch (IOException e) {
            tampilNotif("Gagal membuka dashboard!");
        }
    }

    @FXML
    public void otwRegister(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/registrasi/registrasi.fxml")));
        stage.setScene(scene);
        stage.setWidth(800); 
        stage.setHeight(600);
    }

    private void tampilNotif(String pesan) {
        Notif.setText(pesan);
        Notif.setStyle("-fx-text-fill: red;");
        Notif.setVisible(true);
    }
}
