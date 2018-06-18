package utils;

import gui.DialogUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlUtils {
    public static Pane fxmlLoad(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        try {
            return loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        return null;
    }
}
