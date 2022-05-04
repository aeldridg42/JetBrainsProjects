package encryptdecrypt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

abstract class Cryptography {
    protected char[] text;
    protected Writer writer;
    protected int shift;

    public Cryptography(int shift, Writer writer) {
        this.shift = shift;
        this.writer = writer;
        text = Main.text.toCharArray();
    }

    public abstract void cypher() throws IOException;
}

class Shift extends Cryptography {
    private final int CORRECTION;
    public Shift(int shift, Writer writer) {
        super(shift, writer);
        CORRECTION = shift > 0 ? -26 : 26;
    }

    @Override
    public void cypher() throws IOException {
        BufferedWriter bw = new BufferedWriter(writer);
        for (int i = 0; i < text.length; i++) {
            int tmp = text[i];
            if ((tmp >= 65 && tmp <= 90) || (tmp >= 97 && tmp <= 122)) {
                if (Character.isLowerCase(tmp)) {
                    tmp += shift;
                    while (!(tmp >= 97 && tmp <= 122))
                        tmp += CORRECTION;
                } else {
                    tmp += shift;
                    while (!(tmp >= 65 && tmp <= 90))
                        tmp += CORRECTION;
                }
            }
            text[i] = (char) tmp;
        }
        bw.write(String.valueOf(text) + "\n");
        bw.flush();
        bw.close();
    }
}

class Unicode extends Cryptography {
    public Unicode(int shift, Writer writer) {
        super(shift, writer);
    }

    @Override
    public void cypher() throws IOException {
        BufferedWriter bw = new BufferedWriter(writer);
        for (int i = 0; i < text.length; i++)
            text[i] += shift;
        bw.write(String.valueOf(text) + "\n");
        bw.flush();
        bw.close();
    }
}