package registrasi;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
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
    private TextField NIM;

    @FXML
    private Label Notif;

    @FXML
    private Button btnDaftar;

    @FXML
    private TextField email;

    @FXML
    private RadioButton rbLakiLaki;

    @FXML
    private RadioButton rbPerempuan;

    @FXML
    private TextField namaLengkap;

    @FXML
    private TextField noHP;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    @FXML
    void btnDaftar(ActionEvent event) {
        String User = username.getText();
        String Pass = password.getText();
        String Nama = namaLengkap.getText();
        String Nim = NIM.getText();
        String No = noHP.getText();
        String Email = email.getText();
        String jenisKelamin = getPilihGender();



        if (User.isEmpty() || Pass.isEmpty() || Nama.isEmpty() || Nim.isEmpty() ||
            No.isEmpty() || Email.isEmpty() || jenisKelamin.isEmpty()) {
            tampilNotif("Form tidak boleh kosong!", false);
            return;
        }

        String query = "INSERT INTO users (username, password, nama_lengkap, nim, no_hp, email, jenis_kelamin) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, User);
            stmt.setString(2, Pass);
            stmt.setString(3, Nama);
            stmt.setString(4, Nim);
            stmt.setString(5, No);
            stmt.setString(6, Email);
            stmt.setString(7, jenisKelamin);
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

    private String getPilihGender() {
        if (rbLakiLaki.isSelected()) {
            return "Laki-laki";
        } else if (rbPerempuan.isSelected()) {
            return "Perempuan";
        }
        return "";
    }
}
