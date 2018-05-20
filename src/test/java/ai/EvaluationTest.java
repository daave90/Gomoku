package ai;

import data.Board;
import data.HumanPalyer;
import data.Player;
import org.junit.Assert;
import org.junit.Test;

public class EvaluationTest {

    @Test
    public void evaluateTest(){
        Board board = new Board(15, 15);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(1,2,player1);
        board.setMove(2,2,player1);
        board.setMove(5,1,player1);
        board.setMove(6,1,player1);
        board.setMove(5,2,player1);
        board.setMove(6,2,player1);
        board.setMove(2,5,player1);
        board.setMove(3,5,player1);
        board.setMove(4,5,player1);
        board.setMove(2,6,player1);
        board.setMove(2,7,player1);
        board.setMove(8,5,player1);
        board.setMove(9,5,player1);
        board.setMove(10,5,player1);
        board.setMove(8,8,player1);
        board.setMove(0,14,player1);
        board.setMove(1,13,player1);
        board.setMove(2,12,player1);
        board.setMove(3,11,player1);

        board.setMove(4,0,player2);
        board.setMove(5,0,player2);
        board.setMove(6,0,player2);
        board.setMove(7,0,player2);
        board.setMove(4,1,player2);
        board.setMove(4,2,player2);
        board.setMove(7,5,player2);
        board.setMove(11,5,player2);
        board.setMove(9,9,player2);
        board.setMove(10,9,player2);
        board.setMove(9,10,player2);
        board.setMove(10,10,player2);

        Assert.assertEquals(7, Evaluation.countNotBlockedTwo(board, player1));
        Assert.assertEquals(6, Evaluation.countBlockedTwo(board, player1));
        Assert.assertEquals(2, Evaluation.countNotBlockedThree(board, player1));
        Assert.assertEquals(1, Evaluation.countBlockedFour(board, player1));

        Assert.assertEquals(1, Evaluation.countBlockedThree(board, player2));
        Assert.assertEquals(1, Evaluation.countNotBlockedFour(board, player2));
        Assert.assertEquals(5, Evaluation.countNotBlockedTwo(board, player2));
        Assert.assertEquals(2, Evaluation.countBlockedTwo(board, player2));

        Assert.assertEquals(1650, Evaluation.evaluate(board, player1));
        Assert.assertEquals(1050, Evaluation.evaluate(board, player2));

    }

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

    @Test
    public void countBlockedTwoTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(3,1,player2);
        board.setMove(3,3,player2);
        board.setMove(1,3,player2);
        board.setMove(0,0,player2);
        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(1,2,player1);
        board.setMove(2,2,player1);
        board.setMove(6,0,player1);
        board.setMove(6,1,player1);
        board.setMove(6,3,player1);
        board.setMove(7,3,player1);
        board.setMove(4,5,player1);
        board.setMove(5,5,player1);

        Assert.assertEquals(4,Evaluation.countBlockedTwo(board,player1));
    }

    @Test
    public void countBlockedThreeTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(1,0,player2);
        board.setMove(0,1,player2);
        board.setMove(0,0,player2);
        board.setMove(4,0,player2);
        board.setMove(1,1,player1);
        board.setMove(2,1,player1);
        board.setMove(3,1,player1);
        board.setMove(1,2,player1);
        board.setMove(1,3,player1);
        board.setMove(2,2,player1);
        board.setMove(3,3,player1);
        board.setMove(6,0,player1);
        board.setMove(6,1,player1);
        board.setMove(6,2,player1);
        board.setMove(5,5,player1);
        board.setMove(6,5,player1);
        board.setMove(7,5,player1);
        board.setMove(0,7,player1);
        board.setMove(1,6,player1);
        board.setMove(2,5,player1);

        Assert.assertEquals(7,Evaluation.countBlockedThree(board,player1));
    }

    @Test
    public void countBlockedFourTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,0,player2);
        board.setMove(1,0,player2);
        board.setMove(5,1,player2);
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
        board.setMove(4,7,player1);
        board.setMove(5,7,player1);
        board.setMove(6,7,player1);
        board.setMove(7,7,player1);
        board.setMove(3,6,player1);
        board.setMove(4,6,player1);
        board.setMove(5,6,player1);
        board.setMove(6,6,player1);
        board.setMove(6,5,player1);
        board.setMove(6,4,player1);

        Assert.assertEquals(5,Evaluation.countBlockedFour(board,player1));

    }
}
