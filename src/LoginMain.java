public class LoginMain extends javafx.application.Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        primaryStage.setTitle("Anomali Sistem");
        primaryStage.setScene(new javafx.scene.Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
