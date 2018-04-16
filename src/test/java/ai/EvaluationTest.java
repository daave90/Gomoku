package ai;

import data.Board;
import data.HumanPalyer;
import data.Player;
import org.junit.Assert;
import org.junit.Test;

public class EvaluationTest {

    @Test
    public void countNotBlockedTwoTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);

        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(4,1,player1);
        board.setMove(5,1,player1);
        board.setMove(1,2,player1);
        board.setMove(2,2,player1);
        board.setMove(4,3,player1);
        board.setMove(7,3,player1);
        board.setMove(1,4,player1);
        board.setMove(2,4,player1);
        board.setMove(1,5,player1);
        board.setMove(3,5,player1);
        board.setMove(2,6,player1);
        board.setMove(3,6,player1);
        board.setMove(4,4,player1);
        board.setMove(7,4,player1);

        Assert.assertEquals(16,Evaluation.countNotBlockedTwo(board,player1));
    }

    @Test
    public void countNotBlockedThreeTest(){
        Board board = new Board(5,5);
        Player player1 = new HumanPalyer(1);

        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(3,1,player1);
        board.setMove(1,2,player1);
        board.setMove(1,3,player1);
        board.setMove(2,2,player1);
        board.setMove(3,3,player1);

        Assert.assertEquals(4,Evaluation.countNotBlockedThree(board,player1));
    }

    @Test
    public void countNotBlockedFourTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);

        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(3,1,player1);
        board.setMove(4,1,player1);
        board.setMove(1,2,player1);
        board.setMove(1,3,player1);
        board.setMove(1,4,player1);
        board.setMove(2,2,player1);
        board.setMove(3,3,player1);
        board.setMove(4,4,player1);
        board.setMove(3,4,player1);
        board.setMove(4,3,player1);
        board.setMove(5,2,player1);
        board.setMove(6,1,player1);

        Assert.assertEquals(4,Evaluation.countNotBlockedFour(board,player1));

    }


}
