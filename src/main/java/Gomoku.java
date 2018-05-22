import data.Board;
import data.ComputerPlayer;
import data.WinnerFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Const;

public class Gomoku extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Const.currentStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SetupGameLayout.fxml"));
        Const.currentStage.setTitle("Gomoku standard");
        Const.currentStage.setScene(new Scene(root));
        Const.currentStage.show();
    }
}
