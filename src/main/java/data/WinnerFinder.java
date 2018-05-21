package data;

public class WinnerFinder {

    /**
     * Funkcja sprawdza czy jeden z graczy zwyciężył
     * @param player - gracz który ma zostać sprawdzony pod kątem zwycięstwa
     * @return true - jeżeli wskazany gracz wygrał, false - w przeciwnym wypadku
     */
    public static boolean isWinner(Board board, Player player){
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(checkAskewDown(i, j, board, player)){return true;}
                if(checkAskewUp(i, j, board, player)){return true;}
                if(checkHorizontally(i, j, board, player)){return true;}
                if(checkVertically(i, j, board, player)){return true;}
            }
        }
        return false;
    }

    /**
     *Funkcja sprawdza wygraną w linji poziomej prowadzonej od punktu o współrzędnych (x, y)
     */
    private static boolean checkHorizontally(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(x + 1 >= board.getWidth() ||
                x + 2 >= board.getWidth() ||
                x + 3 >= board.getWidth() ||
                x + 4 >= board.getWidth()){ return false; }

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y) == player.getId() &&
                board.getField(x + 2, y) == player.getId() &&
                board.getField(x + 3, y) == player.getId() &&
                board.getField(x + 4, y) == player.getId()){ return true; }
        return false;
    }

    /**
     *Funkcja sprawdza wygraną w linji pionowej prowadzonej od punktu o współrzędnych (x, y)
     */
    private static boolean checkVertically(int x, int y, Board board, Player player) {
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if (y + 1 >= board.getHeight() ||
                y + 2 >= board.getHeight() ||
                y + 3 >= board.getHeight() ||
                y + 4 >= board.getHeight()) {
            return false;
        }
        if (board.getField(x, y) == player.getId() &&
                board.getField(x, y + 1) == player.getId() &&
                board.getField(x, y + 2) == player.getId() &&
                board.getField(x, y + 3) == player.getId() &&
                board.getField(x, y + 4) == player.getId()) { return true; }
        return false;
    }

    /**
     *Funkcja sprawdza wygraną w linji ukośnej prowadzonej w dół od punktu o współrzędnych (x, y)
     */
    private static boolean checkAskewDown(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || x + 1 >= board.getWidth() ||
                y + 2 >= board.getHeight() || x + 2 >= board.getWidth() ||
                y + 3 >= board.getHeight() || x + 3 >= board.getWidth() ||
                y + 4 >= board.getHeight() || x + 4 >= board.getWidth()){return false;}
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y + 1) == player.getId() &&
                board.getField(x + 2, y + 2) == player.getId() &&
                board.getField(x + 3, y + 3) == player.getId() &&
                board.getField(x + 4, y + 4) == player.getId()){ return true; }
        return false;
    }

    /**
     *Funkcja sprawdza wygraną w linji ukośnej prowadzonej w górę od punktu o współrzędnych (x, y)
     */
    private static boolean checkAskewUp(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y - 1 < 0 || x + 1 >= board.getWidth() ||
                x + 2 >= board.getWidth() || y - 2 < 0 ||
                x + 3 >= board.getWidth() || y - 3 < 0 ||
                x + 4 >= board.getWidth() || y - 4 < 0){return false;}
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y - 1) == player.getId() &&
                board.getField(x + 2, y - 2) == player.getId() &&
                board.getField(x + 3, y - 3) == player.getId() &&
                board.getField(x + 4, y - 4) == player.getId()){ return true; }
        return false;
    }
}
