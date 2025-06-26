package matakuliah;

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

public class MatakuliahController {

    @FXML private TableView<getter> tableMatkul;
    @FXML private TableColumn<getter, Integer> colId;
    @FXML private TableColumn<getter, String> colKodeMatkul;
    @FXML private TableColumn<getter, String> colNamaMatkul;
    @FXML private TableColumn<getter, String> colSks;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKodeMatkul.setCellValueFactory(new PropertyValueFactory<>("kodeMatkul"));
        colNamaMatkul.setCellValueFactory(new PropertyValueFactory<>("namaMatkul"));
        colSks.setCellValueFactory(new PropertyValueFactory<>("sks"));

        showData();
    }

    private void showData() {
        try (Connection conn = DatabaseConnection.connect();
             ResultSet rs = conn.createStatement().executeQuery("SELECT id, kode_matakuliah, nama_matakuliah, sks FROM matakuliah")) {
            
            var data = FXCollections.<getter>observableArrayList();
            
            while (rs.next()) {
                data.add(new getter(
                    rs.getInt("id"),
                    rs.getString("kode_matakuliah"),
                    rs.getString("nama_matakuliah"),
                    rs.getString("sks")));
            }
            
            tableMatkul.setItems(data);
            
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

    @FXML
    public void btnTambahMatkul(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/tambahMatkul/addMatakuliah.fxml")));
        stage.setScene(scene);
        stage.setWidth(800); 
        stage.setHeight(600);
    }
}