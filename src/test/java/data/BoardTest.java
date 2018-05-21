package data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BoardTest {

    @Test
    public void setMoveTest(){
        Player nextPlayer = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(15,15, nextPlayer, prevPlayer);

        board.setMove(14,14, board.getNextPlayer());
        Assert.assertEquals(1, board.getField(14,14));
        Assert.assertTrue(board.getNextPlayer() == prevPlayer);
        Assert.assertTrue(board.getPrevPlayer() == nextPlayer);

        board.setMove(0,3, board.getNextPlayer());
        Assert.assertEquals(2, board.getField(0,3));
        Assert.assertTrue(board.getNextPlayer() == nextPlayer);
        Assert.assertTrue(board.getPrevPlayer() == prevPlayer);

        try{
            board.setMove(15,15, board.getNextPlayer());
            Assert.fail("Wyjątek nie został rzucony");
        }catch (IndexOutOfBoundsException e){}
    }

    @Test
    public void lookAroundTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(3,3,player, prevPlayer);

        List<Field> test1 = board.lookAround(new Field(0,1,1));
        List<Field> test2 = board.lookAround(new Field(0,0,0));
        List<Field> test3 = board.lookAround(new Field(0,1,2));

        Assert.assertEquals( 8, test1.size());
        Assert.assertEquals( 3, test2.size());
        Assert.assertEquals( 5, test3.size());
    }

    @Test
    public void getEmptiesTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(3,3,player, prevPlayer);

        board.setMove(0,0, player);
        board.setMove(0,1, player);
        board.setMove(1,0, player);

        List<Field> empties = board.getEmpties();

        Assert.assertTrue(empties.size() == 6);
    }

    @Test
    public void copyBoardTest(){
        Player player = new HumanPalyer(1);
        Player prevPlayer = new HumanPalyer(2);
        Board board = new Board(3,3,player, prevPlayer);

        board.setMove(0,0, board.getNextPlayer());
        board.setMove(0,1, board.getNextPlayer());
        board.setMove(1,0, board.getNextPlayer());

        Board newBoard = board.copyBoard();
        newBoard.setMove(2,2,player);
        Assert.assertTrue(newBoard.getField(2,2) == 1);
        Assert.assertTrue(board.getField(2,2) == 0);
    }
}
