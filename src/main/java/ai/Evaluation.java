package ai;

import data.Board;
import data.Player;
import utils.Const;

public class Evaluation {

    /**
     * Funkcja zlicza ilość wystąpień nieblokowanych dwójek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
   public static int countNotBlockedTwo(Board board, Player player){
       int count = 0;
       for(int i = 0; i < board.getWidth(); i++){
           for(int j = 0; j < board.getHeight(); j++){
               if(NotBlockedTwo.checkVertically(i,j,board,player)){count++;}
               if(NotBlockedTwo.checkHorizontally(i,j,board,player)){count++;}
               if(NotBlockedTwo.checkAskewDown(i,j,board,player)){count++;}
               if(NotBlockedTwo.checkAskewUp(i,j,board,player)){count++;}
           }
       }
       return count;
   }

    public static int countNotBlockedThree(Board board, Player player){
       int count = 0;
       return count;
    }

    public static int countNotBlockedFour(Board board, Player player){
        int count = 0;
        return count;
    }

    public static int countBlockedTwo(Board board, Player player){
        int count = 0;
        return count;
    }

    public static int countBlockedThree(Board board, Player player){
        int count = 0;
        return count;
    }

    public static int countBlockedFour(Board board, Player player){
        int count = 0;
        return count;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych czwórek
 */
class BlockedFour{

}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych trójek
 */
class BlockedThree{

}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych dwójek
 */
class BlockedTwo{

}

/**
 * Klasa odpowiedzialna za rozpoznawanie nieblokowanych czwórek
 */
class NotBlockedFour{

}

/**
 * Klasa odpowiedzialna za rozpoznawanie nieblokowanych trójek
 */
class NotBlockedThree{

}

/**
 * Klasa odpowiedzialna za rozpoznawanie nieblokowanych dwójek
 */
class NotBlockedTwo{
    /**
     * Funkcja sprawdza horyzontalnie czy wystąpiła nieblokowana dwójka.
     * @param x - współrzędne punktu
     * @param y - współrzędne punktu
     * @param board - plansza
     * @param player - gracz, który ma być sprawdzony pod kątem występienia sytuacji
     * @return
     */
    public static boolean checkHorizontally(int x, int y, Board board, Player player){
        // sprawdź czy wprowadzone współrzędne posadają pole z lewej i z prawej
        if(x - 1 < 0 || x + 2 >= board.getWidth()){return false;}

        //warunek sprawdzający czy pola obok siebie są zajęte przez tego samego gracza
        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y) == player.getId()){

            //waruek sprawdzający czy pola zewnętrzne są puste
            if(board.getField(x - 1, y) == Const.EMPTY_FIELD && board.getField(x + 2, y) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }
    /**
     * Funkcja sprawdza wertykalnie czy wystąpiła nieblokowana dwójka.
     * @param x - współrzędne punktu
     * @param y - współrzędne punktu
     * @param board - plansza
     * @param player - gracz, który ma być sprawdzony pod kątem występienia sytuacji
     * @return
     */
    public static boolean checkVertically(int x, int y, Board board, Player player){
        // sprawdź czy wprowadzone współrzędne posadają pole z lewej i z prawej
        if(y - 1 < 0 || y + 2 >= board.getHeight()){return false;}

        //warunek sprawdzający czy pola obok siebie są zajęte przez tego samego gracza
        if(board.getField(x, y) == player.getId() && board.getField(x, y + 1) == player.getId()){

            //waruek sprawdzający czy pola zewnętrzne są puste
            if(board.getField(x, y - 1) == Const.EMPTY_FIELD && board.getField(x, y + 2) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    /**
     * Funkcja sprawdza ukośnie w dół czy wystąpiła nieblokowana dwójka.
     * @param x - współrzędne punktu
     * @param y - współrzędne punktu
     * @param board - plansza
     * @param player - gracz, który ma być sprawdzony pod kątem występienia sytuacji
     * @return
     */
    public static boolean checkAskewDown(int x, int y, Board board, Player player){
        // sprawdź czy wprowadzone współrzędne posadają pole z lewej i z prawej
        if(y - 1 < 0 || x - 1 < 0 || y + 2 >=board.getHeight() || x + 2 >= board.getWidth()){return false;}

        //warunek sprawdzający czy pola obok siebie są zajęte przez tego samego gracza
        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y + 1) == player.getId()){

            //waruek sprawdzający czy pola zewnętrzne są puste
            if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD && board.getField(x + 2, y + 2) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    /**
     * Funkcja sprawdza ukośnie w górę czy wystąpiła nieblokowana dwójka.
     * @param x - współrzędne punktu
     * @param y - współrzędne punktu
     * @param board - plansza
     * @param player - gracz, który ma być sprawdzony pod kątem występienia sytuacji
     * @return
     */
    public static boolean checkAskewUp(int x, int y, Board board, Player player){
        // sprawdź czy wprowadzone współrzędne posadają pole z lewej i z prawej
        if(y - 2 < 0 || x - 1 < 0 || y + 1 >= board.getHeight() || x + 2 >= board.getWidth()){return false;}

        //warunek sprawdzający czy pola obok siebie są zajęte przez tego samego gracza
        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y - 1) == player.getId()){

            //waruek sprawdzający czy pola zewnętrzne są puste
            if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD && board.getField(x + 2, y - 2) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }
}
