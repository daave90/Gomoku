package data;

public abstract class Player {
    private int id;

    public Player(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract void makeMove(Board board);
}
