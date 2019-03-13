import java.util.Scanner;
/**
 * The accessed class for most functions of the battleship game
 *
 * @author Nickolas Cosentino
 * @version 11/20/2017
 */
public class BattleshipField
{
    public int [][] battleField;
    public int [] shipHits = new int[4];
    public Scanner kb = new Scanner(System.in);
    
    public BattleshipField(int leng, int wid) {
         battleField = new int[leng][wid];
    }
    
    public BattleshipField() {
         battleField = new int[6][6];
    }

    public boolean markCoords(int y, int x) {
        x--; y--;
        if (isValid(x, y)) {
            if (isAHit(x, y)) {
                battleField[x][y] = 5;
                if (battleField[x][y] == 2) {
                    System.out.println("The ship Titanic has been hit!");
                }
                else if (battleField[x][y] == 3) {
                    System.out.println("The ship Destroyer has been hit!");
                }
                else if (battleField[x][y] == 4) {
                    System.out.println("The ship Ricky Smells has been hit!");
                } else {
                    return false;
                }
            } else {
                battleField[x][y] = 6;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isAHit(int x, int y) {
        if (2 <= battleField[x][y] && battleField[x][y] <= 4) {
            int i = battleField[x][y];
            shipHits[i - 1]++;
            if (shipHits[i - 1] == i) {
                shipHits[0]++;
                if (battleField[x][y] == 2) {
                    System.out.println("The ship Titanic has been Sunk!");
                }
                else if (battleField[x][y] == 3) {
                    System.out.println("The ship Destroyer has been Sunk!");
                }
                else if (battleField[x][y] == 4) {
                    System.out.println("The ship Ricky Smells has been Sunk!");
                }
            }
            return true;
        } else if (battleField[x][y] == 0) {
            return false;
        } else {
            return false;
        }
    }
    
    public boolean isValid(int x, int y) {
        if (x < 1 || x > 6 || y < 1 || y > 6) {
            return false; 
        } else {
            return true;
        }
    }

    public void placeShip(int startY, int startX, int direction, int shipLength) {
        startX--;
        startY--;
        for (int i = 0; i < shipLength; i++) {
            if (direction == 1) {
               this.battleField[startY - i][startX] = shipLength;
           } else if (direction == 2) {
               this.battleField[startY + i][startX] = shipLength;
           } else if (direction == 3) {
               this.battleField[startY][startX + i] = shipLength;
           } else if (direction == 4) {
               this.battleField[startY][startX - i] = shipLength;
           }
        }
    }
}


