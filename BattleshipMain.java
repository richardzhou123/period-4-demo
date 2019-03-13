import java.util.Scanner;
/**
 * Main method for the battleship game
 *
 * @author Richard Zhou
 * @version 11/20/17
 */
public class BattleshipMain
{
    public static void main(String [] args)
    {
        int p1score = 0;
        int p2score = 0;
        Scanner kb = new Scanner(System.in);
        int choice = 1;
        do {
             BattleshipGame game = new BattleshipGame();
             int winner = game.playGame();
             if (winner == 1) {
                 p1score ++;
                 System.out.println("Congratulations! Player 1 Won! \t Don't worry Player 2, it's mostly a game of luck.");
             } else if (winner == 2) {
                 p2score++;
                 System.out.println("Congratulations! Player 2 Won! \t Don't worry Player 1, it's mostly a game of luck.");
             }
             System.out.println("p1 has" + p1score);
             System.out.println("p2 has" + p2score);
             System.out.println("Thank you for playing!");
             System.out.println();
             try {
                Thread.sleep(2000);
             }
             catch(InterruptedException ex) {
                 Thread.currentThread().interrupt();
             }
             System.out.println('\u000c');
             System.out.println("Do you want to play again?");
             System.out.println("1 for Yes");
             System.out.println("2 for No");
             choice = kb.nextInt();
        } while (choice == 1);
    }
}


