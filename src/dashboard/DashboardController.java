package dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    @FXML
    private Button btnLogout;

    @FXML 
    private Label menuDataUser;

    @FXML 
    private Label menuPengguna;

    @FXML 
    private Label menuMatakuliah;

    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/logout.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openDataUser(MouseEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/data/data.fxml")));

            stage.setScene(scene);
            stage.setWidth(800);
            System.out.println("Button diklik!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMatakuliah(MouseEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/matakuliah/matakuliah.fxml")));

            stage.setScene(scene);
            stage.setWidth(800);
            System.out.println("Button diklik!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}