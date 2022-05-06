package minesweeper;

import java.util.Random;

public class Mines {
    public void setMines(int mines) {
        Random rand = new Random();
        while (mines-- > 0) {
            int y = rand.nextInt(Main.FIELD_SIZE);
            int x = rand.nextInt(Main.FIELD_SIZE);
            if (Main.field[y][x] == 'X') {
                mines++;
                continue;
            }
            Main.field[y][x] = 'X';
        }
    }

    public void setMinesAround() {
        for (int i = 0; i < Main.FIELD_SIZE; i++) {
            for (int j = 0; j < Main.FIELD_SIZE; j++) {
                if (Main.field[i][j] != 'X')
                    Main.field[i][j] = minesAround(i, j);
            }
        }
    }

    private static char minesAround(int y, int x) {
        int count = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i >= 0 && j >= 0 && i < Main.FIELD_SIZE && j < Main.FIELD_SIZE) {
                    if (Main.field[i][j] == 'X')
                        ++count;
                }
            }
        }
        return count > 0 ? (char) (count + 48) : '.';
    }

}
