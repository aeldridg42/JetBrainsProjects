package encryptdecrypt;

import java.io.*;

public class Main {
    private static int shift;
    private static int mode;
    private static int in = -1;
    private static int out = -1;
    public static String text = "";
    private static String alg = "shift";

    public static void main(String[] args) throws IOException {
        if (args.length < 6 || args.length > 10 || args.length % 2 == 1)
            exit();
        Writer writer = new OutputStreamWriter(System.out);

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                        mode = i + 1;
                        break;
                case "-key":
                        shift = Integer.parseInt(args[i + 1]);
                        break;
                case "-data":
                        text = args[i + 1];
                        break;
                case "-in":
                        in = i + 1;
                        break;
                case "-out":
                        out = i + 1;
                        break;
                case "-alg":
                        alg = args[i + 1];
                        break;
                default: exit(args[i]);
            }
        }
        if (in != -1) {
            BufferedReader br = new BufferedReader(new FileReader(args[in]));
            while (br.ready()) {
                text += br.readLine();
            }
            br.close();
        }
        if (out != -1)
            writer = new FileWriter(args[out]);

        CryptoFactory helper = new CryptoFactory(args[mode], alg);
        helper.getCryptographyInstance(shift, writer).cypher();

        writer.close();
    }

    public static void exit() {
        System.err.println("Wrong arguments. Try again");
        System.exit(-1);
    }

    public static void exit(String string) {
        System.err.println("Wrong argument - " + string);
        System.exit(-1);
    }
}

