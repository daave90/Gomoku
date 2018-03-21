package data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HumanPalyer extends Player {

    public HumanPalyer(int id){
        super(id);
    }

    @Override
    public int[] makeMove() {
        throw new NotImplementedException();
    }
}
