package ai;

import data.Board;
import data.HumanPalyer;
import data.Player;
import org.junit.Assert;
import org.junit.Test;

public class NotBlockedTwoTest {
    @Test
    public void isNotBlocked2HorizontallyTest(){
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);
        Board board = new Board(6,6, player1, player2);

        board.setMove(1,0,player1);
        board.setMove(2,0,player1);
        Assert.assertTrue(NotBlockedTwo.checkHorizontally(1,0,board,player1));

        board.setMove(3,1,player1);
        board.setMove(4,1,player1);
        board.setMove(5,1,player2);
        Assert.assertFalse(NotBlockedTwo.checkHorizontally(3,1,board,player1));
        Assert.assertFalse(NotBlockedTwo.checkHorizontally(5,1,board,player2));

        board.setMove(1,2,player1);
        board.setMove(2,2,player2);
        Assert.assertFalse(NotBlockedTwo.checkHorizontally(1,2,board,player1));

        board.setMove(4,3,player1);
        board.setMove(5,3,player1);
        Assert.assertFalse(NotBlockedTwo.checkHorizontally(4,3,board,player1));

        board.setMove(0,4,player1);
        board.setMove(1,4,player1);
        Assert.assertFalse(NotBlockedTwo.checkHorizontally(0,4,board,player1));

        board.setMove(3,5,player2);
        board.setMove(4,5,player2);
        Assert.assertTrue(NotBlockedTwo.checkHorizontally(3,5,board,player2));

    }

    @Test
    public void isNotBlocked2VerticallyTest(){
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);
        Board board = new Board(6,6 , player1, player2);

        board.setMove(0,0,player1);
        board.setMove(0,1,player1);
        Assert.assertFalse(NotBlockedTwo.checkVertically(0,0,board,player1));

        board.setMove(1,1,player1);
        board.setMove(1,2,player1);
        Assert.assertTrue(NotBlockedTwo.checkVertically(1,1,board,player1));

        board.setMove(2,1,player1);
        board.setMove(2,2,player1);
        board.setMove(2,3,player2);
        Assert.assertFalse(NotBlockedTwo.checkVertically(2,1,board,player1));

        board.setMove(3,1,player2);
        board.setMove(3,2,player1);
        board.setMove(3,3,player1);
        Assert.assertFalse(NotBlockedTwo.checkVertically(3,1,board,player1));

        board.setMove(4,1,player1);
        board.setMove(4,2,player1);
        board.setMove(4,3,player1);
        Assert.assertFalse(NotBlockedTwo.checkVertically(4,1,board,player1));

        board.setMove(5,3,player1);
        board.setMove(5,4,player1);
        Assert.assertTrue(NotBlockedTwo.checkVertically(5,3,board,player1));
    }

    @Test
    public void isNotBlocked2AskewDown(){
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);
        Board board = new Board(6,6, player1, player2);

        board.setMove(0,0,player1);
        board.setMove(1,1,player1);
        Assert.assertFalse(NotBlockedTwo.checkAskewDown(0,0,board,player1));

        board.setMove(3,1,player1);
        board.setMove(4,2,player1);
        Assert.assertTrue(NotBlockedTwo.checkAskewDown(3,1,board,player1));

        board.setMove(4,0,player1);
        board.setMove(5,1,player2);
        Assert.assertFalse(NotBlockedTwo.checkAskewDown(4,0,board,player1));

        board.setMove(5,0,player1);
        Assert.assertFalse(NotBlockedTwo.checkAskewDown(5,0,board,player1));

        board.setMove(3,3,player1);
        board.setMove(4,4,player1);
        Assert.assertTrue(NotBlockedTwo.checkAskewDown(3,3,board,player1));

        board.setMove(1,3,player1);
        board.setMove(2,4,player1);
        board.setMove(3,5,player2);
        Assert.assertFalse(NotBlockedTwo.checkAskewDown(1,3,board,player1));
    }

    @Test
    public void isNotBlocked2AskewUp(){
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);
        Board board = new Board(6,6, player1, player2);

        board.setMove(0,0,player1);
        Assert.assertFalse(NotBlockedTwo.checkAskewUp(0,0,board,player1));

        board.setMove(0,1,player1);
        board.setMove(1,0,player1);
        Assert.assertFalse(NotBlockedTwo.checkAskewUp(0,1,board,player1));

        board.setMove(1,2,player1);
        board.setMove(2,1,player1);
        board.setMove(3,0,player2);
        Assert.assertFalse(NotBlockedTwo.checkAskewUp(1,2,board,player1));

        board.setMove(1,4,player1);
        board.setMove(2,3,player1);
        Assert.assertTrue(NotBlockedTwo.checkAskewUp(1,4,board,player1));

        board.setMove(1,5,player1);
        board.setMove(2,4,player1);
        Assert.assertFalse(NotBlockedTwo.checkAskewUp(1,5,board,player1));
    }
}
