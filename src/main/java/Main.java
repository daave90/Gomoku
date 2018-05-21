import data.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new ComputerPlayer(1,1);
        Player player2 = new HumanPalyer(2);

        Board board = new Board(15,15, player, player2);

        Scanner sc = new Scanner(System.in);

        System.out.println("współrzędne: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        board.setMove(x,y,player2);
        for(int i = 0; i < board.getWidth(); i++){
            for(int j = 0; j < board.getHeight(); j++){
                System.out.print(board.getField(i,j) + " ");
            }
            System.out.println();
        }

        while(true){
            System.out.println();
            if(WinnerFinder.isWinner(board, player)){
                System.out.println("Wygrał gracz o id = " + player.getId());
                break;
            }
            player.makeMove(board);
            for(int i = 0; i < board.getWidth(); i++){
                for(int j = 0; j < board.getHeight(); j++){
                    System.out.print(board.getField(i,j) + " ");
                }
                System.out.println();
            }
            if(WinnerFinder.isWinner(board, player2)){
                System.out.println("Wygrał gracz o id = " + player2.getId());
                break;
            }
            System.out.println();
            System.out.println("współrzędne: ");
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            board.setMove(x2,y2,player2);
            for(int i = 0; i < board.getWidth(); i++){
                for(int j = 0; j < board.getHeight(); j++){
                    System.out.print(board.getField(i,j) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        sc.close();

    }
}
