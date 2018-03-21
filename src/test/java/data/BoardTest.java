package data;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void setMoveTest(){
        Board board = new Board(15,15);
        Player player = new HumanPalyer(1);

        board.setMove(14,14, player);
        Assert.assertEquals(1, board.getField(14,14));

        board.setMove(0,3, player);
        Assert.assertEquals(1, board.getField(0,3));

        try{
            board.setMove(15,15, player);
            Assert.fail("Wyjątek nie został rzucony");
        }catch (IndexOutOfBoundsException e){}
    }

    @Test
    public void isWinnerTest(){

        Player player = new HumanPalyer(1);

        /*horyzontalnie*/
        Board horizontal = new Board(6,6);
        horizontal.setMove(1, 2, player);
        horizontal.setMove(2, 2, player);
        horizontal.setMove(3, 2, player);
        horizontal.setMove(4, 2, player);
        horizontal.setMove(5, 2, player);
        Assert.assertTrue(horizontal.isWinner(player));

        Board horizontal2 = new Board(6,6);
        horizontal.setMove(5, 2, player);
        horizontal.setMove(4, 2, player);
        horizontal.setMove(2, 2, player);
        horizontal.setMove(3, 2, player);
        Assert.assertFalse(horizontal2.isWinner(player));

        /*wertykalnie*/
        Board vertical = new Board(6,6);
        vertical.setMove(3,1,player);
        vertical.setMove(3,2,player);
        vertical.setMove(3,3,player);
        vertical.setMove(3,4,player);
        vertical.setMove(3,5,player);
        Assert.assertTrue(vertical.isWinner(player));

        Board vertical2 = new Board(6,6);
        vertical.setMove(3,2,player);
        vertical.setMove(3,3,player);
        vertical.setMove(3,4,player);
        vertical.setMove(3,5,player);
        Assert.assertFalse(vertical2.isWinner(player));

        /*ukośnie*/
        Board askew = new Board(6,6);
        askew.setMove(1,5,player);
        askew.setMove(2,4,player);
        askew.setMove(3,3,player);
        askew.setMove(4,2,player);
        askew.setMove(5,1,player);
        Assert.assertTrue(askew.isWinner(player));

        Board askew2 = new Board(6,6);
        askew2.setMove(1,0,player);
        askew2.setMove(2,1,player);
        askew2.setMove(3,2,player);
        askew2.setMove(4,3,player);
        askew2.setMove(5,4,player);
        int[][] board = askew2.getBoard();


        Board askew3 = new Board(6,6);
        askew3.setMove(1,0,player);
        askew3.setMove(3,2,player);
        askew3.setMove(4,3,player);
        askew3.setMove(5,4,player);
        Assert.assertFalse(askew3.isWinner(player));

        Board askew4 = new Board(6,6);
        askew4.setMove(1,0,player);
        askew4.setMove(2,1,player);
        askew4.setMove(3,2,player);
        askew4.setMove(5,4,player);
        Assert.assertFalse(askew4.isWinner(player));
    }
}
