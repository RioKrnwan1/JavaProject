package tambahmatkul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddMatkulController {

    @FXML private Label Notif;
    @FXML private Button btnTambah;
    @FXML private TextField kodeMatkul;
    @FXML private TextField namaMatkul;
    @FXML private TextField sks;
    @FXML
    void btnTambah(ActionEvent event) {
        String Kode = kodeMatkul.getText();
        String Nama = namaMatkul.getText();
        String Sks = sks.getText();

        if (Kode.isEmpty() |Nama.isEmpty() || Sks.isEmpty()) {
            tampilNotif("Form tidak boleh kosong!", false);
            return;
        }

        String query = "INSERT INTO matakuliah (kode_matakuliah, nama_matakuliah, sks) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, Kode);
            stmt.setString(2, Nama);
            stmt.setInt(3, Integer.parseInt(Sks));
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                tampilNotif("Tambah matkul berhasil!", true);
            } else {
                tampilNotif("Tambah matkul gagal!", false);
            }

            } catch (SQLException e) {
                tampilNotif("Gagal menyimpan ke database!", false);
                e.printStackTrace();
            } catch (NumberFormatException e) {
            tampilNotif("SKS harus berupa angka!", false);
        }

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
