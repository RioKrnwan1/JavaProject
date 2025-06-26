package data;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class DataController {

    @FXML private TableView<getter> tableUser;
    @FXML private TableColumn<getter, Integer> colId;
    @FXML private TableColumn<getter, String> colUsername;
    @FXML private TableColumn<getter, String> colPassword;
    @FXML private TableColumn<getter, String> colNama;
    @FXML private TableColumn<getter, String> colNIM;
    @FXML private TableColumn<getter, String> colHp;
    @FXML private TableColumn<getter, String> colEmail;
    @FXML private TableColumn<getter, String> colKelamin;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
        colNIM.setCellValueFactory(new PropertyValueFactory<>("NIM"));
        colHp.setCellValueFactory(new PropertyValueFactory<>("noHp"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colKelamin.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));

        showData();
    }

    private void showData() {
        try (Connection conn = DatabaseConnection.connect();
             ResultSet rs = conn.createStatement().executeQuery("SELECT id, username, password, nama_lengkap, nim, no_hp, email, jenis_kelamin FROM users")) {
            
            var data = FXCollections.<getter>observableArrayList();
            
            while (rs.next()) {
                data.add(new getter(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama_lengkap"), 
                    rs.getString("nim"),
                    rs.getString("no_hp"),
                    rs.getString("email"),
                    rs.getString("jenis_kelamin")));
            }
            
            tableUser.setItems(data);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnKembali(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/dashboard/dashboard.fxml")));
        stage.setScene(scene);
        stage.setWidth(800); 
        stage.setHeight(600);
    }
}