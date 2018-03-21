package data;

import utils.Const;

/**
 * Klasa reprezentująca planszę. Posiada pola przechowujące szerokość i wysokość planszy
 * Posiada również funkcję sprawdzającą czy został wyłoniony zwycięzca
 */
public class Board {
    private int [][] board;
    private int height;
    private int width;

    public Board(int height, int width){
        this.height = height;
        this.width = width;

        board = new int[width][height]; // stworzenie nowej planszy
        for(int i = 0; i < width; i++){ // ustawienie pustej wartości dla każdego pola
            for(int j = 0; j < height; j++){
                board[i][j] = Const.EMPTY_FIELD;
            }
        }
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
        }
        else{
            throw new IndexOutOfBoundsException("Plansza nie posiada takich współrzędnych");
        }
    }

    /**
     * Funkcja sprawdza czy jeden z graczy zwyciężył
     * @param player - gracz który ma zostać sprawdzony pod kątem zwycięstwa
     * @return true - jeżeli wskazany gracz wygrał, false - w przeciwnym wypadku
     */
    public boolean isWinner(Player player){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(checkHorizontally(i, j, player) || checkVertically(i, j, player) || checkAskewDown(i, j, player) || checkAskewUp(i, j, player)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *Funkcja sprawdza wygraną w linji poziomej prowadzonej od punktu o współrzędnych (x, y)
     */
    private boolean checkHorizontally(int x, int y, Player player){
        for(int i = x; i < x + 4; i++){
            //jeżeli nie ma ciągłości w którymś momencie sprawdzania
            //lub nie można sprawdzić pięciu pól bo następuje koniec planszy, zwróć false
            if(board[i][y] != player.getId() || i + 1 >= width){
                return false;
            }
        }
        return true;
    }

    /**
     *Funkcja sprawdza wygraną w linji pionowej prowadzonej od punktu o współrzędnych (x, y)
     */
    private boolean checkVertically(int x, int y, Player player){
        for(int i = y; i < y + 4; i++){
            //jeżeli nie ma ciągłości w którymś momencie sprawdzania
            //lub nie można sprawdzić pięciu pól bo następuje koniec planszy, zwróć false
            if(board[x][i] != player.getId() || i + 1 >= height){
                return false;
            }
        }
        return true;
    }

    /**
     *Funkcja sprawdza wygraną w linji ukośnej prowadzonej w dół od punktu o współrzędnych (x, y)
     */
    private boolean checkAskewDown(int x, int y, Player player){
        for(int i = x; i < x + 4; i++){
            //jeżeli nie ma ciągłości w którymś momencie sprawdzania
            //lub nie można sprawdzić pięciu pól bo następuje koniec planszy, zwróć false
            if(board[i][y] != player.getId() || i + 1 >=width || y + 1 >= height){
                return false;
            }
            y++;
        }
        return true;
    }

    /**
     *Funkcja sprawdza wygraną w linji ukośnej prowadzonej w górę od punktu o współrzędnych (x, y)
     */
    private boolean checkAskewUp(int x, int y, Player player){
        for(int i = x; i < x + 4; i++){
            //jeżeli nie ma ciągłości w którymś momencie sprawdzania
            //lub nie można sprawdzić pięciu pól bo następuje koniec planszy, zwróć false
            if(board[i][y] != player.getId() || i + 1 >=width || y - 1 < 0){
                return false;
            }
            y--;
        }
        return true;
    }
}
