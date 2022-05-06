package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int FIELD_SIZE = 9;
    public static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    public static char[][] userField = new char[FIELD_SIZE][FIELD_SIZE];
    public static int minesCounter;
    public static int dots = 0;

    static {
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                userField[y][x] = '.';
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("How many mines do you want on the field?[1-20]");
            minesCounter = scanner.nextInt();
            if (minesCounter < 1 || minesCounter > 20)
                System.out.println("Please enter number between 1 and 20");
        } while (minesCounter < 1 || minesCounter > 20);

        Mines mines = new Mines();
        mines.setMines(minesCounter);
        mines.setMinesAround();

        lyfeCycle(scanner);

        System.out.println("Congratulations! You found all the mines!");
    }

    private static void lyfeCycle(Scanner scanner) {
        int x = 0, y = 0;
        boolean mode;
        while (true) {
            FieldEdit.printer(userField);
            if (dots - minesCounter == 0)
                break;
            do {
                System.out.println("Set/unset mines marks or claim a cell as free:");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                mode = scanner.next().equals("mine");
                if (Character.isDigit(userField[y][x]))
                    System.out.println("There is a number here!");
                else
                    break;
            } while (true);
            if (mode) {
                if (userField[y][x] == '.') {
                    minesCounter += field[y][x] == 'X' ? -1 : 1;
                    userField[y][x] = '*';
                } else if (userField[y][x] == '/') {
                    System.out.println("there is no mine there");
                }
                else {
                    minesCounter += field[y][x] == 'X' ? 1 : -1;
                    userField[y][x] = '.';
                }
            } else {
                FieldEdit.fill(y, x);
            }
        }
    }
}