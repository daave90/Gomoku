package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

/**
 * Klasa odpowiedzialna za wyswietlanie okien dialogowych w trakcie
 * dzialania programu
 */
public class DialogUtils {
    public static void getErrorDialog(String message){
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Blad aplikacji");
        errorDialog.setHeaderText("Wystapil blad podczas dzialania programu.");
        TextArea textArea = new TextArea(message);
        errorDialog.getDialogPane().setContent(textArea);
        errorDialog.showAndWait();
    }

    public static void getInformationDialog(String header, String message){
        Alert errorDialog = new Alert(Alert.AlertType.INFORMATION);
        errorDialog.setTitle(header);
        errorDialog.setHeaderText(message);
        errorDialog.showAndWait();
    }

    public static void errorDialog(String message){
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Błąd aplikacji");
        TextArea textArea = new TextArea(message);
        errorDialog.getDialogPane().setContent(textArea);
        errorDialog.showAndWait();
    }
}
