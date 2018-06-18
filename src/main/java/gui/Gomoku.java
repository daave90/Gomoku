package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FxmlUtils;

public class Gomoku extends Application {

    public static Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene root = new Scene (FxmlUtils.fxmlLoad("/fxml/SetupGameLayout.fxml"));
        stage.setTitle("Gomoku standard");
        stage.setScene(root);
        stage.show();
    }
}
