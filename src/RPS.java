//when Vid falls into the Linda pit, Vid must play a game of rock, paper, scissors to keep going

import java.util.*;

public class RPS {

    private static boolean playerWin;

    //this method will return a String value that will be used in the Game class to determine what should happen next
    // (remove a card, die, no effect, play again if tie)
    //it is probably a good idea to print out the return of this method so the player knows what is going on
    public static void RPS() {
        playerWin = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your move.");
        String VidMove = scan.next();

        //input validation

        while(!(VidMove.equalsIgnoreCase("rock") || VidMove.equalsIgnoreCase("paper") || VidMove.equalsIgnoreCase("scissors"))) {
            System.out.println("Invalid input.");
            System.out.println("Enter your move.");
            VidMove = scan.next();
        }

        int LindaNum = (int) (Math.random() * 3);
        String LindaMove = "";
        if (LindaNum == 0) {
            LindaMove = "rock";
        } else if (LindaNum == 1) {
            LindaMove = "paper";
        } else if (LindaNum == 2) {
            LindaMove = "scissors";
        }
        System.out.println("Linda got " + LindaMove);

        if (VidMove.equals("rock")) {
            if (LindaMove.equals("rock")) {
                System.out.println("Linda and Vid did a tie");
                RPS();
            } else if (LindaMove.equals("paper")) {
                System.out.println("Linda wins. Rip Vid");
            } else if (LindaMove.equals("scissors")) {
                System.out.println("Vid wins. You escaped the Linda pit.");
                playerWin = true;
            }
        } else if (VidMove.equals("paper")) {
            if (LindaMove.equals("rock")) {
                System.out.println("Vid wins. You escaped the Linda pit.");
                playerWin = true;
            } else if (LindaMove.equals("paper")) {
                System.out.println("Linda and Vid did a tie");
                RPS();
            } else if (LindaMove.equals("scissors")) {
                System.out.println("Linda wins. Rip Vid");
            }
        } else if (VidMove.equals("scissors")) {
            if (LindaMove.equals("rock")) {
                System.out.println("Linda wins. Rip Vid");
            } else if (LindaMove.equals("paper")) {
              System.out.println("Vid wins, you escaped the Linda pit.");
                playerWin = true;
            } else if (LindaMove.equals("scissors")) {
                System.out.println("Linda and Vid did a tie");
                RPS();
            }
        } //close else if
    } //close method

    //boolean return whether or not the RPS game was a victory for the player
    public static boolean getWin(){
        return playerWin;
    }
}