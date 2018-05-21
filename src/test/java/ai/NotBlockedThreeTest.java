package ai;

import data.Board;
import data.HumanPalyer;
import data.Player;
import org.junit.Assert;
import org.junit.Test;

public class NotBlockedThreeTest {
    @Test
    public void checkHorizontallyTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(6,6,player, prevPlayer);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(1,0, player1);
        board.setMove(2,0, player1);
        board.setMove(3,0, player1);
        Assert.assertTrue(NotBlockedThree.checkHorizontally(1,0,board,player1));

        board.setMove(2,1, player1);
        board.setMove(3,1, player1);
        board.setMove(4,1, player1);
        board.setMove(5,1, player2);
        Assert.assertFalse(NotBlockedThree.checkHorizontally(2,1,board,player1));

        board.setMove(3,2, player1);
        board.setMove(4,2, player1);
        board.setMove(5,2, player1);
        Assert.assertFalse(NotBlockedThree.checkHorizontally(3,2,board,player1));

        board.setMove(1,4, player2);
        board.setMove(2,4, player1);
        board.setMove(3,4, player1);
        board.setMove(4,4, player1);
        Assert.assertFalse(NotBlockedThree.checkHorizontally(1,4,board,player1));

        board.setMove(2,3, player1);
        board.setMove(3,3, player1);
        board.setMove(5,3, player1);
        Assert.assertFalse(NotBlockedThree.checkHorizontally(2,3,board,player1));
    }

    @Test
    public void checkVerticallyTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(6,6,player, prevPlayer);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,1, player1);
        board.setMove(0,2, player1);
        board.setMove(0,3, player1);
        Assert.assertTrue(NotBlockedThree.checkVertically(0,1,board,player1));

        board.setMove(1,1, player1);
        board.setMove(1,2, player1);
        board.setMove(1,3, player1);
        board.setMove(1,4, player2);
        Assert.assertFalse(NotBlockedThree.checkVertically(1,1,board,player1));

        board.setMove(2,0, player2);
        board.setMove(2,1, player1);
        board.setMove(2,2, player1);
        board.setMove(2,3, player1);
        Assert.assertFalse(NotBlockedThree.checkVertically(2,0,board,player1));

        board.setMove(3,0, player1);
        board.setMove(3,1, player1);
        board.setMove(3,2, player1);
        Assert.assertFalse(NotBlockedThree.checkVertically(3,0,board,player1));

        board.setMove(4,1, player1);
        board.setMove(4,2, player1);
        board.setMove(4,4, player1);
        Assert.assertFalse(NotBlockedThree.checkVertically(4,1,board,player1));
    }

    @Test
    public void checkAskewUpTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(6,6,player, prevPlayer);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(0,3, player1);
        board.setMove(1,2, player1);
        board.setMove(2,1, player1);
        board.setMove(3,0, player2);
        Assert.assertFalse(NotBlockedThree.checkAskewUp(0,3,board,player1));

        board.setMove(1,3, player1);
        board.setMove(2,2, player1);
        board.setMove(4,0, player1);
        Assert.assertFalse(NotBlockedThree.checkAskewUp(1,3,board,player1));

        board.setMove(1,4, player1);
        board.setMove(2,3, player1);
        board.setMove(3,2, player1);
        Assert.assertTrue(NotBlockedThree.checkAskewUp(1,4,board,player1));

        board.setMove(1,5, player1);
        board.setMove(2,4, player1);
        board.setMove(3,3, player1);
        Assert.assertFalse(NotBlockedThree.checkAskewUp(1,5,board,player1));

    }

    @Test
    public void checkAskewDownTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(6,6,player, prevPlayer);
        Player player1 = new HumanPalyer(1);
        Player player2 = new HumanPalyer(2);

        board.setMove(1,2, player1);
        board.setMove(2,3, player1);
        board.setMove(4,5, player1);
        Assert.assertFalse(NotBlockedThree.checkAskewDown(1,2,board,player1));

        board.setMove(1,1, player1);
        board.setMove(2,2, player1);
        board.setMove(3,3, player1);
        Assert.assertTrue(NotBlockedThree.checkAskewDown(1,1,board,player1));

        board.setMove(2,1, player1);
        board.setMove(3,2, player1);
        board.setMove(4,3, player1);
        board.setMove(5,4, player2);
        Assert.assertFalse(NotBlockedThree.checkAskewDown(2,1,board,player1));

        board.setMove(3,0, player1);
        board.setMove(4,1, player1);
        board.setMove(5,2, player1);
        Assert.assertFalse(NotBlockedThree.checkAskewDown(3,0,board,player1));
    }

}
