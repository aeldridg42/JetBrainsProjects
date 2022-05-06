package minesweeper;

public class FieldEdit {
    public static void fill(int y, int x) {
        if (Character.isDigit(Main.field[y][x])) {
            Main.userField[y][x] = Main.field[y][x];
        } else if (Main.field[y][x] == 'X') {
            for (int i = 0; i < Main.FIELD_SIZE; i++) {
                for (int j = 0; j < Main.FIELD_SIZE; j++) {
                    if (Main.field[i][j] == 'X')
                        Main.userField[i][j] = 'X';
                }
            }
            printer(Main.userField);
            System.out.println("You stepped on a mine and failed!");
            System.exit(0);
        } else {
            floodFill(y, x);
        }
    }

    public static void floodFill(int y, int x) {
        if (y < 0 || x < 0 || y >= Main.FIELD_SIZE || x >= Main.FIELD_SIZE ||
                Character.isDigit(Main.userField[y][x]) || Main.userField[y][x] == '/')
            return;
        if (Character.isDigit(Main.field[y][x])) {
            Main.userField[y][x] = Main.field[y][x];
            return;
        }
        else
            Main.userField[y][x] = '/';
        floodFill(y - 1, x);
        floodFill(y, x - 1);
        floodFill(y, x + 1);
        floodFill(y + 1, x);
        floodFill(y - 1, x - 1);
        floodFill(y + 1, x + 1);
        floodFill(y - 1, x + 1);
        floodFill(y + 1, x - 1);
    }

    public static void printer(char[][] field) {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        Main.dots = 0;
        for (int y = 0; y < Main.FIELD_SIZE; y++) {
            System.out.print((y + 1) + "|");
            for (int x = 0; x < Main.FIELD_SIZE; x++) {
                System.out.print(field[y][x]);
                if (field[y][x] == '.')
                    Main.dots++;
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
}
