package ai;

import data.Board;
import data.HumanPalyer;
import data.Player;
import org.junit.Assert;
import org.junit.Test;

public class BolckedTwoTest {

    @Test
    public void checkHorizontallyTest(){
        Board board = new Board(6,6);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,0,player1);
        board.setMove(1,0,player1);
        Assert.assertTrue(BlockedTwo.checkHorizontally(0,0,board,player1));

        board.setMove(4,0,player1);
        board.setMove(5,0,player1);
        Assert.assertTrue(BlockedTwo.checkHorizontally(4,0,board,player1));

        board.setMove(1,2,player1);
        board.setMove(2,2,player1);
        Assert.assertFalse(BlockedTwo.checkHorizontally(1,2,board,player1));

        board.setMove(1,3,player2);
        board.setMove(2,3,player1);
        board.setMove(3,3,player1);
        Assert.assertTrue(BlockedTwo.checkHorizontally(2,3,board,player1));

        board.setMove(1,4,player1);
        board.setMove(2,4,player1);
        board.setMove(3,4,player2);
        Assert.assertTrue(BlockedTwo.checkHorizontally(1,4,board,player1));

        board.setMove(1,5,player2);
        board.setMove(2,5,player1);
        board.setMove(3,5,player1);
        board.setMove(4,5,player2);
        Assert.assertFalse(BlockedTwo.checkHorizontally(2,5,board,player1));
    }

    @Test
    public void checkVerticallyTest(){
        Board board = new Board(6,6);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,0,player1);
        board.setMove(0,1,player1);
        Assert.assertTrue(BlockedTwo.checkVertically(0,0,board,player1));

        board.setMove(0,4,player1);
        board.setMove(0,5,player1);
        Assert.assertTrue(BlockedTwo.checkVertically(0,4,board,player1));

        board.setMove(1,1,player1);
        board.setMove(1,2,player1);
        Assert.assertFalse(BlockedTwo.checkVertically(1,1,board,player1));

        board.setMove(2,1,player2);
        board.setMove(2,2,player1);
        board.setMove(2,3,player1);
        Assert.assertTrue(BlockedTwo.checkVertically(2,2,board,player1));

        board.setMove(3,1,player1);
        board.setMove(3,2,player1);
        board.setMove(3,3,player2);
        Assert.assertTrue(BlockedTwo.checkVertically(3,1,board,player1));

        board.setMove(4,1,player2);
        board.setMove(4,2,player1);
        board.setMove(4,3,player1);
        board.setMove(4,4,player2);
        Assert.assertFalse(BlockedTwo.checkVertically(4,2,board,player1));
    }

    @Test
    public void checkAskewDownTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(6,0,player1);
        board.setMove(7,1,player1);
        Assert.assertFalse(BlockedTwo.checkAskewDown(6,0,board,player1));

        board.setMove(4,0,player1);
        board.setMove(5,1,player1);
        Assert.assertTrue(BlockedTwo.checkAskewDown(4,0,board,player1));

        board.setMove(0,0,player2);
        board.setMove(1,1,player1);
        board.setMove(2,2,player1);
        Assert.assertTrue(BlockedTwo.checkAskewDown(1,1,board,player1));

        board.setMove(4,3,player1);
        board.setMove(5,4,player1);
        board.setMove(6,5,player2);
        Assert.assertTrue(BlockedTwo.checkAskewDown(4,3,board,player1));

        board.setMove(0,4,player2);
        board.setMove(1,5,player1);
        board.setMove(2,6,player1);
        board.setMove(3,7,player2);
        Assert.assertFalse(BlockedTwo.checkAskewDown(1,5,board,player1));
    }

    @Test
    public void checkAskewUpTest(){
        Board board = new Board(8,8);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,1,player1);
        board.setMove(1,0,player1);
        Assert.assertFalse(BlockedTwo.checkAskewUp(0,1,board,player1));

        board.setMove(0,2,player1);
        board.setMove(1,1,player1);
        Assert.assertTrue(BlockedTwo.checkAskewUp(0,2,board,player1));

        board.setMove(1,3,player2);
        board.setMove(2,2,player1);
        board.setMove(3,1,player1);
        Assert.assertTrue(BlockedTwo.checkAskewUp(2,2,board,player1));

        board.setMove(1,4,player1);
        board.setMove(2,3,player1);
        board.setMove(3,2,player2);
        Assert.assertTrue(BlockedTwo.checkAskewUp(1,4,board,player1));

        board.setMove(1,5,player1);
        board.setMove(2,4,player1);
        Assert.assertFalse(BlockedTwo.checkAskewUp(1,5,board,player1));

        board.setMove(2,6,player2);
        board.setMove(3,5,player1);
        board.setMove(4,4,player1);
        board.setMove(5,3,player2);
        Assert.assertFalse(BlockedTwo.checkAskewUp(3,5,board,player1));

        board.setMove(5,7,player2);
        board.setMove(6,6,player1);
        board.setMove(7,5,player1);
        Assert.assertFalse(BlockedTwo.checkAskewUp(6,6,board,player1));
    }
}
