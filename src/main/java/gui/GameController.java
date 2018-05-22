package gui;

import data.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Const;

import java.io.IOException;
import java.util.Random;


public class GameController {

    @FXML private VBox boardVBox;
    @FXML private ImageView nextPlayerImage;

    private ImageView[][] guiBoard;
    private Board logicBoard;
    private Player playerOne;
    private Player playerTwo;

    @FXML
    public void initialize() {

        //Pobranie stworzonych graczy
        playerOne = Context.getInstance().getPlayerOne();
        playerTwo = Context.getInstance().getPlayerTwo();

        //Wylosowanie gracza rozpoczynającego
        Random random = new Random();
        int choseFirstPlayer = random.nextInt(2 - 1 + 1) + 1;

        //stworzenie nowej planszy logicznej
        if(choseFirstPlayer == 1){
            logicBoard = new Board(Context.getInstance().getBoardHeight(), Context.getInstance().getBoardWidth(), playerOne, playerTwo);
            nextPlayerImage.setImage(new Image(Const.FIRST_PLAYER_IMAGE));
        }
        else if(choseFirstPlayer == 2){
            logicBoard = new Board(Context.getInstance().getBoardHeight(), Context.getInstance().getBoardWidth(), playerTwo, playerOne);
            nextPlayerImage.setImage(new Image(Const.SECOND_PLAYER_IMAGE));
        }

        //Stworzenie widoku planszy
        guiBoard = new ImageView[logicBoard.getWidth()][logicBoard.getHeight()];
        for(int i = 0; i < guiBoard.length; i++){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            for (int j = 0; j < guiBoard[i].length; j++){
                guiBoard[i][j] = new ImageView();
                hBox.getChildren().add(guiBoard[i][j]);
            }
            boardVBox.getChildren().add(hBox);
        }

        //Synchronizacja wartości planszy logicznej i widoku
        parseBoard(logicBoard);
    }

    private void parseBoard(Board logicBoard) {
        for(int i = 0; i < logicBoard.getWidth(); i++){
            for(int j = 0; j < logicBoard.getHeight(); j++){
                if(logicBoard.getField(i,j) == Const.EMPTY_FIELD){
                    guiBoard[i][j].setImage(new Image(Const.EMPTY_FIELD_IMAGE));
                }
                else if(logicBoard.getField(i,j) == Const.FIRST_PLAYER_ID){
                    guiBoard[i][j].setImage(new Image(Const.FIRST_PLAYER_IMAGE));
                }
                else if(logicBoard.getField(i,j) == Const.SECOND_PLAYER_ID){
                    guiBoard[i][j].setImage(new Image(Const.SECOND_PLAYER_IMAGE));
                }
            }
        }
    }

    @FXML
    public void newGameSetup(){
        Parent root = null;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SetupGameLayout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Gomoku standard");
        stage.setMaximized(false);
        stage.setScene(new Scene(root));
        Const.currentStage.close();
        Const.currentStage = stage;
        Const.currentStage.show();
    }

    @FXML
    public void onStartGame(){
        startGame(logicBoard.getNextPlayer(), logicBoard.getPrevPlayer());
    }

    private void startGame(Player first, Player second){
        if(first instanceof ComputerPlayer && second instanceof ComputerPlayer){
            computerVsComputer(first, second);
        }
        else if(first instanceof HumanPalyer && second instanceof HumanPalyer){
            humanVsHuman(first, second);
        }
        else if((first instanceof HumanPalyer && second instanceof ComputerPlayer) ||
                (first instanceof ComputerPlayer && second instanceof HumanPalyer)){
            computerVsHuman(first, second);
        }
    }

    private void computerVsComputer(Player first, Player second){
        while(!WinnerFinder.isWinner(logicBoard, first) &&
                !WinnerFinder.isWinner(logicBoard, second) &&
                !logicBoard.getEmpties().isEmpty()){

            first.makeMove(logicBoard);
            parseBoard(logicBoard);
            if(WinnerFinder.isWinner(logicBoard, first)){
                if(first.getId() == 1){
                    DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły");
                }
                else{
                    DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły");
                }
                break;
            }

            second.makeMove(logicBoard);
            parseBoard(logicBoard);
            if(WinnerFinder.isWinner(logicBoard, second)){
                if(second.getId() == 1){
                    DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!!");
                }
                else{
                    DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!!");
                }
                break;
            }
        }

        if(logicBoard.getEmpties().isEmpty()){
            DialogUtils.getInformationDialog("Koniec gry", "Rozgrywka zakończona remisem!!!");
        }
    }

    private void humanVsHuman(Player first, Player second){

    }

    private void computerVsHuman(Player first, Player second){

    }
}
