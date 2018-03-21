package data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ComputerPlayer extends Player {

    public ComputerPlayer(int id){
        super(id);
    }

    @Override
    public int[] makeMove() {
        throw new NotImplementedException();
    }
}
