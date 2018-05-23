package gui;

import data.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Const;

import java.io.IOException;
import java.util.Random;


public class GameController {

    @FXML private VBox boardVBox;
    @FXML private ImageView nextPlayerImage;
    @FXML private Button newGameButton;

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
            nextPlayerImage.setImage(Const.FIRST_PLAYER_IMAGE);
        }
        else if(choseFirstPlayer == 2){
            logicBoard = new Board(Context.getInstance().getBoardHeight(), Context.getInstance().getBoardWidth(), playerTwo, playerOne);
            nextPlayerImage.setImage(Const.SECOND_PLAYER_IMAGE);
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
                    guiBoard[i][j].setImage(Const.EMPTY_FIELD_IMAGE);
                }
                else if(logicBoard.getField(i,j) == Const.FIRST_PLAYER_ID){
                    guiBoard[i][j].setImage(Const.FIRST_PLAYER_IMAGE);
                }
                else if(logicBoard.getField(i,j) == Const.SECOND_PLAYER_ID){
                    guiBoard[i][j].setImage(Const.SECOND_PLAYER_IMAGE);
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
        newGameButton.setDisable(true);
        startGame(logicBoard.getNextPlayer(), logicBoard.getPrevPlayer());
    }

    private void startGame(Player first, Player second){

        if(first instanceof ComputerPlayer && second instanceof ComputerPlayer){
            computerVsComputer((ComputerPlayer) first, (ComputerPlayer) second);
        }
        else if(first instanceof HumanPalyer && second instanceof HumanPalyer){
            humanVsHuman();
        }
        else if((first instanceof HumanPalyer && second instanceof ComputerPlayer) ||
                (first instanceof ComputerPlayer && second instanceof HumanPalyer)){
            if(first instanceof ComputerPlayer){

                ((ComputerPlayer) first).firstMove(logicBoard);
                parseBoard(logicBoard);

                //zmień obrazek na następnego gracza
                if(logicBoard.getNextPlayer().getId() == Const.FIRST_PLAYER_ID){
                    nextPlayerImage.setImage(Const.FIRST_PLAYER_IMAGE);
                }
                else {
                    nextPlayerImage.setImage(Const.SECOND_PLAYER_IMAGE);
                }

            }
            computerVsHuman(first, second);
        }
    }

    private void computerVsComputer(ComputerPlayer first, ComputerPlayer second){

        long start = System.currentTimeMillis();
        ComputerPlayer computerPlayer = (ComputerPlayer) logicBoard.getNextPlayer();
        computerPlayer.firstMove(logicBoard);
        parseBoard(logicBoard);
        changePicture();

        while(!WinnerFinder.isWinner(logicBoard, logicBoard.getPrevPlayer()) &&
                !WinnerFinder.isWinner(logicBoard, logicBoard.getNextPlayer()) &&
                !logicBoard.getEmpties().isEmpty()){

            computerPlayer = (ComputerPlayer) logicBoard.getNextPlayer();
            computerPlayer.makeMove(logicBoard);
            parseBoard(logicBoard);
            changePicture();
        }

        if(WinnerFinder.isWinner(logicBoard, logicBoard.getPrevPlayer())){
            if(logicBoard.getPrevPlayer().getId() == Const.FIRST_PLAYER_ID){
                long stop = System.currentTimeMillis();
                long time = stop - start;
                DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!! (" + time + " ms)");
            }
            else{
                long stop = System.currentTimeMillis();
                long time = stop - start;
                DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!! (" + time + " ms)");
            }
        }
        else if(WinnerFinder.isWinner(logicBoard, logicBoard.getNextPlayer())){
            if(logicBoard.getNextPlayer().getId() == Const.FIRST_PLAYER_ID){
                long stop = System.currentTimeMillis();
                long time = stop - start;
                DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!! (" + time + " ms)");
            }
            else{
                long stop = System.currentTimeMillis();
                long time = stop - start;
                DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!! (" + time + " ms)");
            }
        }
        else if(logicBoard.getEmpties().isEmpty()){
            long stop = System.currentTimeMillis();
            long time = stop - start;
            DialogUtils.getInformationDialog("Koniec gry", "Rozgrywka zakończona remisem!!! (" + time + " ms)");
        }
    }

    private void humanVsHuman(){
        for(int i = 0; i < guiBoard.length; i++){
            for(int j = 0; j < guiBoard[i].length; j++){
                guiBoard[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        //Znalezienie klikniętego pola
                        for(int i = 0; i < guiBoard.length; i++){
                            for(int j = 0; j < guiBoard[i].length; j++){

                                //jeżeli pole zostało kliknięte i jest puste
                                if(guiBoard[i][j] == event.getSource() &&
                                        guiBoard[i][j].getImage() == Const.EMPTY_FIELD_IMAGE){

                                    //zmień obrazek na gracza wykonującego ruch
                                    guiBoard[i][j].setImage(nextPlayerImage.getImage());

                                    //wykonaj ruch na planszy logicznej
                                    logicBoard.setMove(i, j, logicBoard.getNextPlayer());

                                    //zmień obrazek następnego gracza
                                    changePicture();

                                    break;
                                }
                            }
                        }
                        if(WinnerFinder.isWinner(logicBoard, logicBoard.getPrevPlayer())){
                            if(logicBoard.getPrevPlayer().getId() == Const.FIRST_PLAYER_ID){
                                DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!!");
                            }
                            else{
                                DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!!");
                            }
                            disableImageViews();
                        }
                        else if(logicBoard.getEmpties().isEmpty()){
                            DialogUtils.getInformationDialog("Koniec gry", "Rozgrywka zakończona remisem!!!");
                            disableImageViews();
                        }
                    }
                });
            }
        }
    }

    private void computerVsHuman(Player first, Player second){
        for(int i = 0; i < guiBoard.length; i++){
            for(int j = 0; j < guiBoard[i].length; j++) {
                guiBoard[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        //Gracz komputerowy musi być zawsze drugi ponieważ
                        //To gracz człowiek klikając na pole wywołuje również algorytm min - max
                        if(first instanceof ComputerPlayer){
                            newRound(second, first, event);
                        }
                        else{
                            newRound(first, second, event);
                        }
                    }

                    private void newRound(Player first, Player second, MouseEvent event){

                        //Znalezienie klikniętego pola
                        for(int i = 0; i < guiBoard.length; i++){
                            for(int j = 0; j < guiBoard[i].length; j++){

                                //jeżeli pole zostało kliknięte i jest puste
                                if(guiBoard[i][j] == event.getSource() &&
                                        guiBoard[i][j].getImage() == Const.EMPTY_FIELD_IMAGE){

                                    //zmień obrazek na gracza wykonującego ruch
                                    guiBoard[i][j].setImage(nextPlayerImage.getImage());

                                    //wykonaj ruch na planszy logicznej
                                    logicBoard.setMove(i, j, logicBoard.getNextPlayer());

                                    //zmień obrazek na następnego gracza
                                    changePicture();

                                    //Sprawdzenie zwycięstwa poprzedniego gracza lub remisu
                                    if(WinnerFinder.isWinner(logicBoard, logicBoard.getPrevPlayer())){
                                        if(logicBoard.getPrevPlayer().getId() == Const.FIRST_PLAYER_ID){
                                            DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!!");
                                        }
                                        else{
                                            DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!!");
                                        }
                                        disableImageViews();
                                        break;
                                    }
                                    else if(logicBoard.getEmpties().isEmpty()){
                                        DialogUtils.getInformationDialog("Koniec gry", "Rozgrywka zakończona remisem!!!");
                                        disableImageViews();
                                        break;
                                    }

                                    //Wyłączenie planszy na czas trwania ruchu komputera
                                    disableImageViews();

                                    //Ruch komputera
                                    second.makeMove(logicBoard);
                                    parseBoard(logicBoard);

                                    //Sprawdzenie zwycięstwa poprzedniego gracza lub remisu
                                    if(WinnerFinder.isWinner(logicBoard, logicBoard.getPrevPlayer())){
                                        if(logicBoard.getPrevPlayer().getId() == Const.FIRST_PLAYER_ID){
                                            DialogUtils.getInformationDialog("Koniec gry", "Białe zwyciężyły!!!");
                                        }
                                        else{
                                            DialogUtils.getInformationDialog("Koniec gry", "Czarne zwyciężyły!!!");
                                        }
                                        disableImageViews();
                                        break;
                                    }
                                    else if(logicBoard.getEmpties().isEmpty()){
                                        DialogUtils.getInformationDialog("Koniec gry", "Rozgrywka zakończona remisem!!!");
                                        disableImageViews();
                                        break;
                                    }

                                    //zmień obrazek na następnego gracza
                                    changePicture();

                                    //Włączenie planszy
                                    enableImageViews();
                                    break;
                                }

                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * Funkcja zmienia obrazek w imageView
     */
    private void changePicture(){
        if(logicBoard.getNextPlayer().getId() == Const.FIRST_PLAYER_ID){
            nextPlayerImage.setImage(Const.FIRST_PLAYER_IMAGE);
        }
        else{
            nextPlayerImage.setImage(Const.SECOND_PLAYER_IMAGE);
        }
    }

    private void disableImageViews(){
        for(int i = 0; i < guiBoard.length; i++){
            for(int j = 0;  j < guiBoard[i].length; j++){
                guiBoard[i][j].setDisable(true);
            }
        }
    }

    private void enableImageViews(){
        for(int i = 0; i < guiBoard.length; i++){
            for(int j = 0;  j < guiBoard[i].length; j++){
                guiBoard[i][j].setDisable(false);
            }
        }
    }

}
