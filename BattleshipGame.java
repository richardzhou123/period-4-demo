import java.util.Scanner;
public class BattleshipGame
 /**
  * 
  * The Main Area for the Battleship game to run.
  * Nickolas Cosentino
  * 11/29/2017
  */

{
    public static Scanner kb = new Scanner(System.in);
    public static Scanner names = new Scanner(System.in);
    private static boolean isGameOver = false; 
    
    BattleshipField p1 = new BattleshipField();
    BattleshipField p2 = new BattleshipField();
    
    public int playGame() {
        System.out.println("Welcome, Let's play battleship!");
        System.out.println("Player 1, place your ships.");
        setShips(p1);
        System.out.println("Player 2, place your ships.");
        setShips(p2);
        int i = 1;
        while (isGameOver == false) {
            beginTurn(i % 2);
            i++;
        }
        return 0;
    }
    
    public void beginTurn(int num) {
        System.out.println('\u000c');
        boolean done = false;
        do {
            if (num == 1) {
                displayField(p2.battleField, false);
                System.out.println("Player 1, where would you like to shoot? Type the letter coord, then the number coord (A = 1, B = 2 etc...)");
                int letterCoord = kb.nextInt();
                System.out.println("Now choose a number from 1-6.");
                int numCoord = kb.nextInt();
                done = p2.markCoords(numCoord, letterCoord);
            } else {
                displayField(p1.battleField, false);
                System.out.println("Player 2, where would you like to shoot? Type the letter coord, then the number coord (A = 1, B = 2 etc...)");
                int letterCoord = kb.nextInt();
                System.out.println("Now choose a number from 1-6.");
                int numCoord = kb.nextInt();
                done = p1.markCoords(numCoord, letterCoord);
            }
            if (!done){
                System.out.println('\u000c');
            } else {
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        } while (!done);
    }

    public void setShips(BattleshipField player) {
        int cont = 1;
        do {
            try {
                Thread.sleep(3000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Ship Titanic is 1 x 2, Ship Destroyer is 1 x 3, Ship Ricky Smells is 1 x 4. Use coordinates to indicate which point you would like the ship to start.");
            for (int i = 0; i < 3; i++) {
                displayField(player.battleField, true);
                String [] shipName = {"Titanic (\uD83D\uDEF3)", "Destroyer (\uD83C\uDF86)", "Ricky Smells (\uD83D\uDC43)"};
                System.out.println("Where would you like to place the ship, " + 
                shipName[i] + "? (Type the vertical coordinate, and then the horizontal coordinate.)");
                int letterNum = names.nextInt();
                System.out.println("Now enter a number coord");
                int number = kb.nextInt();
                System.out.println("Which direction do you want the " + shipName[i] + " to face?");
                System.out.println("1 - North");
                System.out.println("2 - South");
                System.out.println("3 - East");
                System.out.println("4 - West");
                int direction = kb.nextInt();
                if (isValid(number, letterNum, direction, i + 2, player)) {
                    player.placeShip(letterNum, number, direction, i + 2);
                    System.out.println("Ship Placed successfully!");
                } else {
                    System.out.println("Error. Place ship again.");
                    i--;
                }
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println('\u000c');
            }
            displayField(player.battleField, true);
            /*
            do {
                System.out.println("Type 0 if your ship placement is correct, or type 1 if you wish to replace your ships");
                cont = kb.nextInt();
            } while (cont != 0 || cont != 1);
            
            if (cont == 1) {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        player.battleField[i][j] = 0;
                    }
                }
            }
            */
        } while (cont == 0);
    }

    public void displayField(int [][] fieldArr, boolean isPlacing) {
        System.out.println("\t1\t2\t3\t4\t5\t6");
        int i = 0;
        for (int [] row: fieldArr) {
            i++;
            System.out.print(toLetter(i) + "\t" );
            for(int col:row) {
                if (isPlacing == true) {
                    if (col == 0) {
                        System.out.print("~");
                    } else if (col == 2) {
                        System.out.print("\uD83D\uDEF3");
                    } else if (col == 3) {
                        System.out.print("\uD83C\uDF86");
                    } else if (col == 4) {
                        System.out.print("\uD83D\uDC43");
                    } 
                } else {
                    if (col >= 2 && col <= 4 || col == 0) {
                        System.out.print("~");
                    } else if (col == 1) {
                        System.out.print("*");
                    } else if (col == 5) {
                        System.out.print("\uD83C\uDF0A");
                    } else if (col == 6) {
                        System.out.println("\u2739");
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("A = 1, B = 2, C = 3, D = 4, E = 5, F = 6");
        System.out.println();
    }
    
    public boolean isValid(int y, int x, int direction, int shipLength, BattleshipField player) {
        shipLength--;
        if (player.battleField[x - 1][y - 1] == 0) {
            if (direction == 1) {
                if (x < 1 || x > 6 || y - shipLength < 1 || y > 6){
                   return false;
                }  else {return true;}
            } else if (direction == 3) {
                if (x < 1 || x + shipLength > 6 || y < 1 || x > 6 ){
                    return false;
                }  else {return true;}
            } else if (direction == 2) {
                if (x < 1 || x > 6 || y < 1 || y + shipLength > 6){
                    return false;
                }  else {return true;}
            } else if (direction == 4) {
                if (x - shipLength < 1 || x > 6 || y < 1 || y > 6){
                    return false;
                }  else {return true;}
            }
        }
        for (int i = 0; i < shipLength; i++) {
            if (direction == 1) {
                if (player.battleField[y + i][x] != 0) {return false;}
            } else if (direction == 2) {
                if (player.battleField[y - i][x] != 0) {return false;}
            } else if (direction == 3) {
                if (player.battleField[y][x + i] != 0) {return false;}
            } else if (direction == 4) {
                if (player.battleField[y][x - i] != 0) {return false;}
            }
        }
        return false;
    }

    public String toLetter(int num) {
        if (num == 1) {
            return "A";
        } else if (num == 2) {
            return "B";
        } else  if (num == 3) {
            return "C";
        } else  if (num == 4) {
            return "D";
        } else if (num == 5) {
            return "E";
        } else if (num == 6) {
            return "F";
        } else {
            return "Invalid";
        }
    }
}

