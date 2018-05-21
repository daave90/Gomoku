package ai;

import data.Board;
import data.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinMax {

    /**
     * Główny algorytm min-max wyposarzony w cięcia alfa-beta.
     * @param board
     * @param deep - głębokość wyszukiwania
     * @param myBest - alpha
     * @param theirBest - beta
     * @return Object[0] zawiera ocenę węzła, Object[1] jest to klasa Field z ruchem gracza.
     */
    public Object[] doMinMaxAlfaBeta(Board board, int deep, double myBest, double theirBest){

        List<Field> moveList;

        Set<Field> moves = new HashSet<>();

        List<Field> places = board.getPlayerPlaces(board.getNextPlayer());

        for (Field place : places) {
            moves.addAll(board.lookAround(place));
        }
        moves.retainAll(board.getEmpties());

        //jeżeli lista moves jest pusta to jako rozważane pola wybierz puste pola na planszy
        if(moves.isEmpty()){
            moveList = new ArrayList<>(board.getEmpties());
        }
        else{
            moveList = new ArrayList<>(moves);
        }

        Double bestScore;
        Object[] temp;
        Double tempScore;
        Field bestMove = new Field(0,0,0);

        //jeżeli liść drzewa to zwróć ocenę węzła
        if(deep == 0){
            Object[] pick = {Evaluation.evaluate(board, board.getNextPlayer()), moveList.get(0)};
            return pick;
        }

        bestScore = myBest;

        while(moveList.size() > 0){
            Board newBoard = board.copyBoard();
            Field newMove = moveList.get(0);
            newBoard.setMove(newMove.getX(), newMove.getY(), newBoard.getNextPlayer());
            temp = doMinMaxAlfaBeta(newBoard, deep - 1, -theirBest, -bestScore);
            tempScore = -(Double) temp[0];
            if(tempScore > bestScore){
                bestScore = tempScore;
                bestMove = newMove;
            }
            if(bestScore > theirBest){
                Object[] pick = {bestScore, bestMove};
                return pick;
            }
            moveList.remove(0);
        }

        Object[] pick = {bestScore, bestMove};
        return pick;
    }
}
