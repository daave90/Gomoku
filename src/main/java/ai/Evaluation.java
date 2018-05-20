package ai;

import data.Board;
import data.Player;
import utils.Const;

public class Evaluation {

    public static int evaluate(Board board, Player player){
        return countNotBlockedTwo(board, player) * 100 +
                countNotBlockedThree(board, player) * 200 +
                countNotBlockedFour(board, player) * 300 +
                countBlockedTwo(board, player) * 50 +
                countBlockedThree(board, player) * 150 +
                countBlockedFour(board, player) * 250;
    }

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

    /**
     * Funkcja zlicza ilość wystąpień nieblokowanych trójek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
    public static int countNotBlockedThree(Board board, Player player){
        int count = 0;
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(NotBlockedThree.checkHorizontally(j, i, board, player)){count++;}
                if(NotBlockedThree.checkVertically(j, i, board, player)){count++;}
                if(NotBlockedThree.checkAskewDown(j, i, board, player)){count++;}
                if(NotBlockedThree.checkAskewUp(j, i, board, player)){count++;}
            }
        }
        return count;
    }

    /**
     * Funkcja zlicza ilość wystąpień nieblokowanych czwórek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
    public static int countNotBlockedFour(Board board, Player player){
        int count = 0;
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(NotBlockedFour.checkHorizontally(j, i, board, player)){count++;}
                if(NotBlockedFour.checkVertically(j, i, board, player)){count++;}
                if(NotBlockedFour.checkAskewDown(j, i, board, player)){count++;}
                if(NotBlockedFour.checkAskewUp(j, i, board, player)){count++;}
            }
        }
        return count;
    }

    /**
     * Funkcja zlicza ilość wystąpień blokowanych dwójek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
    public static int countBlockedTwo(Board board, Player player){
        int count = 0;
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(BlockedTwo.checkHorizontally(j, i, board, player)){count++;}
                if(BlockedTwo.checkVertically(j, i, board, player)){count++;}
                if(BlockedTwo.checkAskewDown(j, i, board, player)){count++;}
                if(BlockedTwo.checkAskewUp(j, i, board, player)){count++;}
            }
        }
        return count;
    }

    /**
     * Funkcja zlicza ilość wystąpień blokowanych trójek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
    public static int countBlockedThree(Board board, Player player){
        int count = 0;
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(BlockedThree.checkHorizontally(j, i, board, player)){count++;}
                if(BlockedThree.checkVertically(j, i, board, player)){count++;}
                if(BlockedThree.checkAskewDown(j, i, board, player)){count++;}
                if(BlockedThree.checkAskewUp(j, i, board, player)){count++;}
            }
        }
        return count;
    }

    /**
     * Funkcja zlicza ilość wystąpień blokowanych czwórek
     * @param board - plansza do analizy
     * @param player - gracz, który jest analizowany
     * @return ilość wystąpień nieblokowanych dwójek
     */
    public static int countBlockedFour(Board board, Player player){
        int count = 0;
        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(BlockedFour.checkHorizontally(j, i, board, player)){count++;}
                if(BlockedFour.checkVertically(j, i, board, player)){count++;}
                if(BlockedFour.checkAskewDown(j, i, board, player)){count++;}
                if(BlockedFour.checkAskewUp(j, i, board, player)){count++;}
            }
        }
        return count;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych czwórek
 */
class BlockedFour{
    public static boolean checkHorizontally(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(x + 1 >= board.getWidth() || x + 2 >= board.getWidth() || x + 3 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if(x - 1 < 0 && x + 4 >= board.getWidth()) {return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y) == player.getId() &&
                board.getField(x + 2, y) == player.getId() &&
                board.getField(x + 3, y) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 4, y) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(x + 4 >= board.getWidth()){
                //pierwsze pole wolne
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD &&
                        board.getField(x + 4, y) != Const.EMPTY_FIELD &&
                        board.getField(x + 4, y) != player.getId()){return true;}
                //drugie pole wolne, pierwsze nie
                else if (board.getField(x - 1, y) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y) != player.getId() &&
                        board.getField(x + 4, y) == Const.EMPTY_FIELD){return true;}
            }
        }
        return false;
    }

    public static boolean checkVertically(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || y + 2 >= board.getHeight() || y + 3 >= board.getHeight()){return false;}

        //nie ma zewnętrznych pól
        if(y - 1 < 0 && y + 4 >= board.getHeight()) {return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x, y + 1) == player.getId() &&
                board.getField(x, y + 2) == player.getId() &&
                board.getField(x, y + 3) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0){
                //drugie pole wolne
                if(board.getField(x, y + 4) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 4 >= board.getHeight()){
                //pierwsze pole wolne
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x, y + 4) != Const.EMPTY_FIELD &&
                        board.getField(x, y + 4) != player.getId()){return true;}
                //drugie pole wolne, pierwsze nie
                else if (board.getField(x, y - 1) != Const.EMPTY_FIELD &&
                        board.getField(x, y - 1) != player.getId() &&
                        board.getField(x, y + 4) == Const.EMPTY_FIELD){return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewDown(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || x + 1 >= board.getWidth() ||
                y + 2 >= board.getHeight() || x + 2 >= board.getWidth() ||
                y + 3 >= board.getHeight() || x + 3 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if((y - 1 < 0 || x - 1 < 0) && (y + 4 >= board.getHeight() || x + 4 >= board.getWidth())){return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y + 1) == player.getId() &&
                board.getField(x + 2, y + 2) == player.getId() &&
                board.getField(x + 3, y + 3) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0 || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 4, y + 4) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 4 >= board.getHeight() || x + 4 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x + 4, y + 4) != Const.EMPTY_FIELD &&
                        board.getField(x + 4, y + 4) != player.getId()) {return true;}
                if(board.getField(x - 1, y - 1) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y - 1) != player.getId() &&
                        board.getField(x + 4, y + 4) == Const.EMPTY_FIELD) {return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewUp(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y - 1 < 0 || x + 1 >= board.getWidth() ||
                x + 2 >= board.getWidth() || y - 2 < 0 ||
                x + 3 >= board.getWidth() || y - 3 < 0){return false;}

        //nie ma zewnętrznych pól
        if((y - 4 < 0 || x + 4 >= board.getWidth()) && (y + 1 >= board.getHeight() || x - 1 < 0)){return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y - 1) == player.getId() &&
                board.getField(x + 2, y - 2) == player.getId() &&
                board.getField(x + 3, y - 3) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y + 1 >= board.getHeight() || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 4, y - 4) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y - 4 < 0 || x + 4 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x + 4, y - 4) == Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) != player.getId()) {return true;}
                if(board.getField(x + 4, y - 4) != Const.EMPTY_FIELD &&
                        board.getField(x + 4, y - 4) != player.getId() &&
                        board.getField(x - 1, y + 1) == Const.EMPTY_FIELD) {return true;}
            }
        }
        return false;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych trójek
 */
class BlockedThree{
    public static boolean checkHorizontally(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(x + 1 >= board.getWidth() || x + 2 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if(x - 1 < 0 && x + 3 >= board.getWidth()) {return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y) == player.getId() &&
                board.getField(x + 2, y) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 3, y) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(x + 3 >= board.getWidth()){
                //pierwsze pole wolne
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD &&
                        board.getField(x + 3, y) != Const.EMPTY_FIELD &&
                        board.getField(x + 3, y) != player.getId()){return true;}
                //drugie pole wolne, pierwsze nie
                else if (board.getField(x - 1, y) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y) != player.getId() &&
                        board.getField(x + 3, y) == Const.EMPTY_FIELD){return true;}
            }
        }
        return false;
    }

    public static boolean checkVertically(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || y + 2 >= board.getHeight()){return false;}

        //nie ma zewnętrznych pól
        if(y - 1 < 0 && y + 3 >= board.getHeight()) {return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x, y + 1) == player.getId() &&
                board.getField(x, y + 2) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0){
                //drugie pole wolne
                if(board.getField(x, y + 3) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 3 >= board.getHeight()){
                //pierwsze pole wolne
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x, y + 3) != Const.EMPTY_FIELD &&
                        board.getField(x, y + 3) != player.getId()){return true;}
                //drugie pole wolne, pierwsze nie
                else if (board.getField(x, y - 1) != Const.EMPTY_FIELD &&
                        board.getField(x, y + 3) == Const.EMPTY_FIELD &&
                        board.getField(x, y - 1) != player.getId()){return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewDown(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || x + 1 >= board.getWidth() ||
                y + 2 >= board.getHeight() || x + 2 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if((y - 1 < 0 || x - 1 < 0) && (y + 3 >= board.getHeight() || x + 3 >= board.getWidth())){return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y + 1) == player.getId() &&
                board.getField(x + 2, y + 2) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0 || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 3, y + 3) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 3 >= board.getHeight() || x + 3 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x + 3, y + 3) != Const.EMPTY_FIELD &&
                        board.getField(x + 3, y + 3) != player.getId()) {return true;}
                if(board.getField(x - 1, y - 1) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y - 1) != player.getId() &&
                        board.getField(x + 3, y + 3) == Const.EMPTY_FIELD) {return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewUp(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y - 1 < 0 || x + 1 >= board.getWidth() ||
                x + 2 >= board.getWidth() || y - 2 < 0){return false;}

        //nie ma zewnętrznych pól
        if((y - 3 < 0 || x + 3 >= board.getWidth()) && (y + 1 >= board.getHeight() || x - 1 < 0)){return false;}

        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y - 1) == player.getId() &&
                board.getField(x + 2, y - 2) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y + 1 >= board.getHeight() || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 3, y - 3) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y - 3 < 0 || x + 3 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x + 3, y - 3) == Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) != player.getId()) {return true;}
                if(board.getField(x + 3, y - 3) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) == Const.EMPTY_FIELD &&
                        board.getField(x + 3, y - 3) != player.getId()) {return true;}
            }
        }
        return false;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie blokowanych dwójek
 */
class BlockedTwo{
    public static boolean checkHorizontally(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(x + 1 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if(x - 1 < 0 && x + 2 >= board.getWidth()) {return false;}

        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 2, y) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(x + 2 >= board.getWidth()){
                //pierwsze pole wolne
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie
                if(board.getField(x - 1, y) == Const.EMPTY_FIELD &&
                        board.getField(x + 2, y) != player.getId() &&
                        board.getField(x + 2, y) != Const.EMPTY_FIELD){return true;}
                //drugie pole wolne, pierwsze nie
                else if (board.getField(x - 1, y) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y) != player.getId() &&
                        board.getField(x + 2, y) == Const.EMPTY_FIELD){return true;}
            }
        }
        return false;
    }

    public static boolean checkVertically(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight()){return false;}

        //nie ma zewnętrznych pól
        if(y - 1 < 0 && y + 2 >= board.getHeight()) {return false;}

        if(board.getField(x, y) == player.getId() && board.getField(x, y + 1) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0){
                //drugie pole wolne
                if(board.getField(x, y + 2) == Const.EMPTY_FIELD) {return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 2 >= board.getHeight()){
                //pierwsze pole wolne
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD) {return true;}
            }

            //obydwa pola istnieją
            else{
                //pierwsze pole wolne, drugie nie jest zajęte przez gracza
                if(board.getField(x, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x, y + 2) != player.getId() &&
                        board.getField(x, y + 2) != Const.EMPTY_FIELD){return true;}
                //drugie pole wolne, pierwsze nie est zajęte przez gracza
                else if (board.getField(x, y - 1) != player.getId() &&
                        board.getField(x, y - 1) != Const.EMPTY_FIELD &&
                        board.getField(x, y + 2) == Const.EMPTY_FIELD){return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewDown(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y + 1 >= board.getHeight() || x + 1 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if((y - 1 < 0 || x - 1 < 0) && (y + 2 >= board.getHeight() || x + 2 >= board.getWidth())){return false;}

        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y + 1) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y - 1 < 0 || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 2, y + 2) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y + 2 >= board.getHeight() || x + 2 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD &&
                        board.getField(x + 2, y + 2) != player.getId() &&
                        board.getField(x + 2, y + 2) != Const.EMPTY_FIELD) {return true;}
                if(board.getField(x - 1, y - 1) != player.getId() &&
                        board.getField(x + 2, y + 2) == Const.EMPTY_FIELD &&
                        board.getField(x - 1, y - 1) != Const.EMPTY_FIELD) {return true;}
            }
        }
        return false;
    }

    public static boolean checkAskewUp(int x, int y, Board board, Player player){
        //sprawdzenie czy posiadamy wszystkie wymagane pola
        if(y - 1 < 0 || x + 1 >= board.getWidth()){return false;}

        //nie ma zewnętrznych pól
        if((y - 2 < 0 || x + 2 >= board.getWidth()) && (y + 1 >= board.getHeight() || x - 1 < 0)){return false;}

        if(board.getField(x, y) == player.getId() && board.getField(x + 1, y - 1) == player.getId()){

            //pierwsze zewnętrzne pole nie istnieje
            if(y + 1 >= board.getHeight() || x - 1 < 0){
                //drugie pole wolne
                if(board.getField(x + 2, y - 2) == Const.EMPTY_FIELD){return true;}
            }

            //drugie zewnętrzne pole nie istnieje
            else if(y - 2 < 0 || x + 2 >= board.getWidth()){
                //pierwsze wolne
                if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD){return true;}
            }

            //obydwa pola istnieją
            else{
                if(board.getField(x + 2, y - 2) == Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) != player.getId() &&
                        board.getField(x - 1, y + 1) != Const.EMPTY_FIELD) {return true;}
                if(board.getField(x + 2, y - 2) != player.getId() &&
                        board.getField(x + 2, y - 2) != Const.EMPTY_FIELD &&
                        board.getField(x - 1, y + 1) == Const.EMPTY_FIELD) {return true;}
            }
        }
        return false;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie nieblokowanych czwórek
 */
class NotBlockedFour {
    public static boolean checkHorizontally(int x, int y, Board board, Player player) {
        //sprawdzenie czy zewnętrzne pola istnieją
        if(x - 1 < 0 || x + 4 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y) == player.getId() &&
                board.getField(x + 2, y)== player.getId() &&
                board.getField(x + 3, y)== player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y) == Const.EMPTY_FIELD && board.getField(x + 4, y) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkVertically(int x, int y, Board board, Player player) {

        //sprawdzenie czy zewnętrzne pola istnieją
        if(y - 1 < 0 || y + 4 >= board.getHeight()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x, y + 1) == player.getId() &&
                board.getField(x, y + 2)== player.getId() &&
                board.getField(x, y + 3) == player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x, y - 1) == Const.EMPTY_FIELD && board.getField(x, y + 4) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkAskewDown(int x, int y, Board board, Player player) {
        //sprawdzenie czy zewnętrzne pola istnieją
        if(y - 1 < 0 || x - 1 < 0 || y + 4 >=board.getHeight() || x + 4 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y + 1) == player.getId() &&
                board.getField(x + 2, y + 2) == player.getId() &&
                board.getField(x + 3, y + 3) == player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD && board.getField(x + 4, y + 4) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkAskewUp(int x, int y, Board board, Player player) {
        //sprawdzenie czy zewnętrzne pola istnieją
        if(y + 1 >= board.getHeight() || x - 1 < 0 || y - 4 < 0 || x + 4 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y - 1) == player.getId() &&
                board.getField(x + 2, y - 2) == player.getId() &&
                board.getField(x + 3, y - 3) == player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD && board.getField(x + 4, y - 4) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }
}

/**
 * Klasa odpowiedzialna za rozpoznawanie nieblokowanych trójek
 */
class NotBlockedThree{
    public static boolean checkHorizontally(int x, int y, Board board, Player player){
        //sprawdzenie czy zewnętrzne pola istnieją
        if(x - 1 < 0 || x + 3 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y) == player.getId() &&
                board.getField(x + 2, y)== player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y) == Const.EMPTY_FIELD && board.getField(x + 3, y) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkVertically(int x, int y, Board board, Player player){

        //sprawdzenie czy zewnętrzne pola istnieją
        if(y - 1 < 0 || y + 3 >= board.getHeight()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x, y + 1) == player.getId() &&
                board.getField(x, y + 2)== player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x, y - 1) == Const.EMPTY_FIELD && board.getField(x, y + 3) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkAskewDown(int x, int y, Board board, Player player){
        //sprawdzenie czy zewnętrzne pola istnieją
        if(y - 1 < 0 || x - 1 < 0 || y + 3 >=board.getHeight() || x + 3 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y + 1) == player.getId() &&
                board.getField(x + 2, y + 2) == player.getId()){

            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y - 1) == Const.EMPTY_FIELD && board.getField(x + 3, y + 3) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }

    public static boolean checkAskewUp(int x, int y, Board board, Player player){
        //sprawdzenie czy zewnętrzne pola istnieją
        if(y + 1 >= board.getHeight() || x - 1 < 0 || y - 3 < 0 || x + 3 >= board.getWidth()){return false;}

        //sprawdzenie czy 3 kolejne pola są zajęte przez gracza
        if(board.getField(x, y) == player.getId() &&
                board.getField(x + 1, y - 1) == player.getId() &&
                board.getField(x + 2, y - 2) == player.getId()){
            //sprawdzenie czy zewnętrzne pola są puste
            if(board.getField(x - 1, y + 1) == Const.EMPTY_FIELD && board.getField(x + 3, y - 3) == Const.EMPTY_FIELD){
                return true;
            }
        }
        return false;
    }
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
