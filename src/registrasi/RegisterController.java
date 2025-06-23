package registrasi;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class RegisterController {

    @FXML
    private Button btnDaftar;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label Notif;

    @FXML
    void btnDaftar(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            tampilNotif("Form tidak boleh kosong!", false);
            return;
        }

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user);
            stmt.setString(2, pass);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                tampilNotif("Register berhasil!", true);
            } else {
                tampilNotif("Register gagal!", false);
            }

        } catch (SQLException e) {
            tampilNotif("Gagal menyimpan ke database!", false);
            e.printStackTrace();
        }
    }
    
    @FXML
    public void otwLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/login/login.fxml")));
        stage.setScene(scene);
        stage.setWidth(800); 
        stage.setHeight(600);
    }

    private void tampilNotif(String pesan, boolean sukses) {
        Notif.setText(pesan);
        if (sukses) {
            Notif.setStyle("-fx-text-fill: green;");
        } else {
            Notif.setStyle("-fx-text-fill: red;");
        }
        Notif.setVisible(true);
    }
}
