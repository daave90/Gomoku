package data;


import ai.MinMax;

public class ComputerPlayer extends Player {

    private MinMax minMax;
    private int deep;

    public ComputerPlayer(int id, int deep){
        super(id);
        this.minMax = new MinMax();
        this.deep = deep;
    }

    @Override
    public void makeMove(Board board) {
        Object[] move = minMax.doMinMaxAlfaBeta(board, deep, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        Field pickMove = (Field) move[1];
        board.setMove(pickMove.getX(), pickMove.getY(), this);
    }
}
