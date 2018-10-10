/* CHALLENGE - Spiral Number Pattern
 * Input any number from user and generate pattern as follow: 
 * 1) Input : 5 
 * Output:   1  2  3  4  5 
 *          16 17 18 19  6 
 *          15 24 25 20  7 
 *          14 23 22 21  8 
 *          13 12 11 10  9 
 * Or you can make a code for this one too. 
 * 2) Input: 15 
 * Output:  7  8  9 10 
 *          6  1  2 11 
 *          5  4  3 12 
 *            15 14 13 
 * Upto what user gives input. 
 */
package spiralnumberpattern;

import java.util.Scanner;

public class SpiralNumberPattern {
    
    private final String[][] spiralPattern;
    private final int maxNumber;
    private final boolean isSquare;

    public static void main(String[] args) {
        SpiralNumberPattern square = new SpiralNumberPattern(
                input("Enter a number for square pattern: "), true);
        System.out.println(square.toString());
        SpiralNumberPattern centered = new SpiralNumberPattern(
                input("Enter a number for centered pattern: "), false);
        System.out.println(centered.toString());
    }
    
    private SpiralNumberPattern(int number, boolean isSquare) {
        this.isSquare = isSquare;
        this.maxNumber = number;
        if (isSquare) {
            this.spiralPattern = new String[number][number];
            this.createSquarePattern();
        } else {
            this.spiralPattern = new String[arraySize()][arraySize()];
            this.createCenteredPattern();
        }
    }
    
    private static int input(String message) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        while (number < 1) {
            System.out.print(message);
            try {
                String nextLine = sc.nextLine();
                number = Integer.parseInt(nextLine);
            } catch (NumberFormatException ex) {
                System.err.println(ex.getMessage() +
                        " You have to enter a positive whole number!");
            }
        }
        return number;
    }

    private void createSquarePattern() {
        int counter = 1;
        int row = 0, column = 0;
        for (int i = 0; i < (this.maxNumber + 1) / 2; i++) {
            for (; column < this.maxNumber - i; column++) {
                this.add(row, column, counter++);
            }
            row++; column--;
            for (; row < this.maxNumber - i; row++) {
                this.add(row, column, counter++);
            }
            row--; column--;
            for (; column >= i; column--) {
                this.add(row, column, counter++);
            }
            row--; column++;
            for (; row > i; row--) {
                this.add(row, column, counter++);
            }
            row++; column++;
        }
    }

    private void createCenteredPattern() {
        int counter = 1, iter = 1;
        int middle = (this.spiralPattern.length - 1) / 2;
        int row = middle, column = middle;
        while (counter <= this.maxNumber && middle + iter < this.spiralPattern.length) {
            for (;column <= middle + iter; column++) {
                if (counter <= this.maxNumber) {
                    this.add(row, column, counter++);
                }
            }
            row++; column--;
            for (; row <= middle + iter; row++) {
                if (counter <= this.maxNumber) {
                    this.add(row, column, counter++);
                }
            }
            row--; column--;
            for (; column >= middle - iter; column--) {
                if (counter <= this.maxNumber) {
                    this.add(row, column, counter++);
                }
            }
            row--; column++;
            for (; row >= middle - iter; row--) {
                if (counter <= this.maxNumber) {
                    this.add(row, column, counter++);
                }
            }
            row++; column++; iter ++;
        }
    }

    private int arraySize() {
        return (int) (Math.sqrt(maxNumber) + 1);
    }
    
    private int add(int row, int column, int number) {
        this.spiralPattern[row][column] = String.valueOf(number);
        return number;
    }

    @Override
    public String toString() {
        String result = "";
        for (String[] spiralPatternRow : this.spiralPattern) {
            for (String value : spiralPatternRow) {
                result += value != null ? value : "";
                result += "\t";
            }
            result += "\n";
        }
        return result;
    }
}
