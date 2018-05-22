package gui;

import data.ComputerPlayer;
import data.HumanPalyer;
import data.Player;
import data.WinnerFinder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import utils.Const;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.function.UnaryOperator;

public class SetupGameController {

    @FXML private ComboBox<String> playerOneType;
    @FXML private ComboBox<String> playerTwoType;
    @FXML private Label playerOneLabel;
    @FXML private Label playerTwoLabel;

    @FXML private TextField playerOneDeep;
    @FXML private TextField playerTwoDeep;
    @FXML private Label playerOneDeepLabel;
    @FXML private Label playerTwoDeepLabel;

    @FXML private Button createPlayers;

    @FXML private TextField boardHeight;
    @FXML private TextField boardWidth;
    @FXML private Label boardLabel;
    @FXML private Label boardHeightLabel;
    @FXML private Label boardWidthLabel;

    @FXML private Button newGameButton;

    @FXML
    public void initialize() {

        //Dodanie wartości do wyboru
        playerOneType.getItems().add("Człowiek");
        playerOneType.getItems().add("Komputer");
        playerTwoType.getItems().add("Człowiek");
        playerTwoType.getItems().add("Komputer");

        //Stworzenie textField przyjmującego jedynie liczby
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            }
            return null;
        };

        //Przypisanie stworzonej klasy textFormatter do odpowiednich kontrolek
        playerOneDeep.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
        playerTwoDeep.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
        boardHeight.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
        boardWidth.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));

        //Wyłączenie kontrolek
        playerOneDeep.setDisable(true);
        playerTwoDeep.setDisable(true);
        playerOneDeepLabel.setDisable(true);
        playerTwoDeepLabel.setDisable(true);
        createPlayers.setDisable(true);
        boardHeight.setDisable(true);
        boardWidth.setDisable(true);
        boardLabel.setDisable(true);
        boardHeightLabel.setDisable(true);
        boardWidthLabel.setDisable(true);
        newGameButton.setDisable(true);
    }

    public void choosePlayerType(){
        String firstPlayerType = playerOneType.getValue();
        String secondPlayerType = playerTwoType.getValue();

        //Jeżeli typ gracza został wybrany to pokaż przycisk
        if(firstPlayerType != null && secondPlayerType != null){
            createPlayers.setDisable(false);
        }

        //jeżeli typ gracza to komputer to pokaż odpowiednie pole edycyjne
        if(firstPlayerType != null && firstPlayerType.equalsIgnoreCase("komputer")){
            playerOneDeep.setDisable(false);
            playerOneDeepLabel.setDisable(false);
        }else{
            playerOneDeep.setDisable(true);
            playerOneDeepLabel.setDisable(true);
        }

        if(secondPlayerType != null && secondPlayerType.equalsIgnoreCase("komputer")){
            playerTwoDeep.setDisable(false);
            playerTwoDeepLabel.setDisable(false);
        }else{
            playerTwoDeep.setDisable(true);
            playerTwoDeepLabel.setDisable(true);
        }
    }

    public void createPlayers(){
        //pobierz typy graczy
        String firstPlayerType = playerOneType.getValue();
        String secondPlayerType = playerTwoType.getValue();
        //jeżeli typ pierwszego gracza to komputer
        if(firstPlayerType.equalsIgnoreCase("komputer")){

            //jeżeli głębokość jest pusta to wyrzuć wyjątek
            if(playerOneDeep.getText().equals("")){
                DialogUtils.getErrorDialog("Podaj głębokość przeszukiwania dla pierwszego gracza");
                throw new InvalidParameterException("Podaj głębokość przeszukiwania dla pierwszego gracza");
            }

            int deep = Integer.valueOf(playerOneDeep.getText());

            //jeżeli głębokość = 0 to wyrzuć wyjątek
            if(deep == 0){
                DialogUtils.getErrorDialog("Głębokość przeszukiwania musi być większa od 0.");
                throw new InvalidParameterException("Głębokość przeszukiwania musi być większa od 0.");
            }

            Context.getInstance().setPlayerOne(new ComputerPlayer(Const.FIRST_PLAYER_ID, deep));

        }
        else if(firstPlayerType.equalsIgnoreCase("człowiek")){
            Context.getInstance().setPlayerOne(new HumanPalyer(Const.FIRST_PLAYER_ID));
        }

        //jeżeli typ drugiego gracza to komputer
        if(secondPlayerType.equalsIgnoreCase("komputer")){

            //jeżeli głębokość jest pusta to wyrzuć wyjątek
            if(playerTwoDeep.getText().equals("")) {
                DialogUtils.getErrorDialog("Podaj głębokość przeszukiwania dla drugiego gracza");
                throw new InvalidParameterException("Podaj głębokość przeszukiwania dla drugiego gracza");
            }

            int deep = Integer.valueOf(playerTwoDeep.getText());

            //jeżeli głębokość = 0 to wyrzuć wyjątek
            if(deep == 0){
                DialogUtils.getErrorDialog("Głębokość przeszukiwania musi być większa od 0.");
                throw new InvalidParameterException("Głębokość przeszukiwania musi być większa od 0.");
            }

            Context.getInstance().setPlayerTwo(new ComputerPlayer(Const.SECOND_PLAYER_ID, deep));

        }
        else if(secondPlayerType.equalsIgnoreCase("człowiek")){
            Context.getInstance().setPlayerTwo(new HumanPalyer(Const.SECOND_PLAYER_ID));
        }

        //Wyłączenie kontrolek
        playerOneLabel.setDisable(true);
        playerTwoLabel.setDisable(true);
        playerOneType.setDisable(true);
        playerTwoType.setDisable(true);

        playerOneDeep.setDisable(true);
        playerTwoDeep.setDisable(true);
        playerOneDeepLabel.setDisable(true);
        playerTwoDeepLabel.setDisable(true);

        createPlayers.setDisable(true);

        //Włączenie kontrolek
        boardHeight.setDisable(false);
        boardWidth.setDisable(false);
        boardLabel.setDisable(false);
        boardHeightLabel.setDisable(false);
        boardWidthLabel.setDisable(false);

        newGameButton.setDisable(false);
    }

    public void startNewGame(){
        if(boardWidth.getText().equals("")){
            DialogUtils.getErrorDialog("Podaj szerokość planszy");
            throw new InvalidParameterException("Podaj szerokość planszy");
        }

        if(boardHeight.getText().equals("")){
            DialogUtils.getErrorDialog("Podaj wysokość planszy");
            throw new InvalidParameterException("Podaj wysokość planszy");
        }

        int width = Integer.valueOf(boardWidth.getText());
        int height = Integer.valueOf(boardHeight.getText());

        if(width < 5 || width > 15){
            DialogUtils.getErrorDialog("Szerokość planszy musi zawierać się w przedziale [5, 15]");
            throw new InvalidParameterException("Szerokość planszy musi zawierać się w przedziale [5, 15]");
        }

        if(height < 5 || height > 15){
            DialogUtils.getErrorDialog("Wysokość planszy musi zawierać się w przedziale [5, 15]");
            throw new InvalidParameterException("Wysokość planszy musi zawierać się w przedziale [5, 15]");
        }

        Context.getInstance().setBoardHeight(width);
        Context.getInstance().setBoardWidth(height);

        //Włączenie kontrolek
        playerOneLabel.setDisable(false);
        playerTwoLabel.setDisable(false);
        playerOneType.setDisable(false);
        playerTwoType.setDisable(false);

        //Wyłączenie kontrolek
        playerOneDeep.setDisable(true);
        playerTwoDeep.setDisable(true);
        playerOneDeepLabel.setDisable(true);
        playerTwoDeepLabel.setDisable(true);

        createPlayers.setDisable(true);

        boardHeight.setDisable(true);
        boardWidth.setDisable(true);
        boardLabel.setDisable(true);
        boardHeightLabel.setDisable(true);
        boardWidthLabel.setDisable(true);

        newGameButton.setDisable(true);

        Parent root = null;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameLayout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Gomoku standard");
        stage.setMaximized(true);
        stage.setScene(new Scene(root));
        Const.currentStage.close();
        Const.currentStage = stage;
        Const.currentStage.show();
    }
}
