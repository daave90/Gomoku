package data;

import utils.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca planszę. Posiada pola przechowujące szerokość i wysokość planszy
 * Posiada również funkcję sprawdzającą czy został wyłoniony zwycięzca
 */
public class Board {
    private int [][] board;
    private int height;
    private int width;
    private Player nextPlayer;
    private Player prevPlayer;


    public Board(int height, int width, Player nextPlayer, Player prevPlayer){
        this.height = height;
        this.width = width;
        this.nextPlayer = nextPlayer;
        this.prevPlayer = prevPlayer;

        board = new int[width][height]; // stworzenie nowej planszy
        for(int i = 0; i < width; i++){ // ustawienie pustej wartości dla każdego pola
            for(int j = 0; j < height; j++){
                board[i][j] = Const.EMPTY_FIELD;
            }
        }
    }

    public Board copyBoard(){
        Board newBoard = new Board(this.height, this.width, this.nextPlayer, this.prevPlayer);
        newBoard.setBoard(this.board);
        return newBoard;
    }

    public int getField(int x, int y){
        return board[x][y];
    }

    public int[][] getBoard() {
        return board;
    }

    /**
     * Funkcja wykonuje ruch na planszy nadając polu o współrzędnych x i y identyfikator gracza
     * @param x - współrzędna x
     * @param y - współrzędna y
     * @param player - gracz wykonujący ruch
     */
    public void setMove(int x, int y, Player player){
        if(x < width && y < height){
            board[x][y] = player.getId();
            this.nextPlayer = prevPlayer;
            this.prevPlayer = player;
        }
        else{
            throw new IndexOutOfBoundsException("Plansza nie posiada takich współrzędnych");
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    private void setBoard(int[][] board) {
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    /**
     * Funkcja zwraca listę pustych pól
     * @return
     */
    public List<Field> getEmpties(){
        List<Field> empties = new ArrayList<>();

        for (int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j] == Const.EMPTY_FIELD){
                    empties.add(new Field(board[i][j], i , j));
                }
            }
        }

        return empties;
    }

    /**
     * Funkcja zwraca pola sąsiadujące z przekazanym w argumencie polem
     * @param field
     * @return
     */
    public List<Field> lookAround(Field field){

        List<Field> fields = new ArrayList<>();

        int x = field.getX();
        int y = field.getY();

        if(x + 1 < width){

            //prawy
            fields.add(new Field(board[x + 1][y], x + 1, y));

            if(y + 1 < height){
                // prawy dolny róg
                fields.add(new Field(board[x + 1][y + 1], x + 1, y + 1));
            }

            if(y - 1 >= 0){
                //prawy górny róg
                fields.add(new Field(board[x + 1][y - 1], x + 1, y - 1));
            }
        }
        if(x - 1 >= 0){

            //lewy
            fields.add(new Field(board[x - 1][y], x - 1, y));

            if(y + 1 < height){
                // lewy dolny róg
                fields.add(new Field(board[x - 1][y + 1], x - 1, y + 1));
            }

            if(y - 1 >= 0){
                //lewy górny róg
                fields.add(new Field(board[x - 1][y - 1], x - 1, y - 1));
            }
        }
        if(y + 1 < height){
            //dół
            fields.add(new Field(board[x][y + 1], x, y + 1));
        }
        if(y - 1 >= 0){
            //góra
            fields.add(new Field(board[x][y - 1], x, y - 1));
        }

        return fields;
    }

    /**
     * Funkcja zwraca pola zajęte przez gracza
     */
    public List<Field> getPlayerPlaces(Player player){
        List<Field> fields = new ArrayList<>();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j] == player.getId()){
                    fields.add(new Field(player.getId(), i, j));
                }
            }
        }

        return fields;
    }
}
